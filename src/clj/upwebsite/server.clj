(ns upwebsite.server
  (:require [optimus.optimizations :as optimizations]
            [optimus.prime :as optimus]
            [optimus.strategies :refer [serve-live-assets serve-frozen-assets]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [stasis.core :as stasis]
            [taoensso.timbre :as log]
            [upwebsite.web :refer [get-pages get-assets context-path]]))

(defonce server (atom nil))

(def dev-app
  (-> (stasis/serve-pages get-pages)
      (optimus/wrap get-assets
                    optimizations/none
                    serve-live-assets)))

(def prod-app
  (-> (stasis/serve-pages get-pages)
      (optimus/wrap get-assets
                    optimizations/all
                    serve-frozen-assets)))

(defn start-server! [& [port mode ctx-path]]
  (if ctx-path (reset! context-path ctx-path))
  (log/debug "port: " (or port 8090))
  (log/debug "mode: " (or mode "dev"))
  (log/debug "context-path: " @context-path)
  (reset! server
          (run-jetty
           (case mode
             "dev" (wrap-reload #'dev-app)
             "prod" (wrap-reload #'prod-app)
             (wrap-reload #'dev-app))
           {:port (if port (Integer/parseInt port) 8090)
            :join? false})))

(defn stop-server! []
  (when @server
    (.stop @server)
    (reset! server nil)))

(def -main start-server!)
