(ns upwebsite.server
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [taoensso.timbre :as log]
            [upwebsite.web :refer [dev-app prod-app]]))

(defonce server (atom nil))

(defn start-server! [& [port mode]]
  (log/debug "Mode: " mode)
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
