(ns upwebsite.static
  (:require
   [stasis.core :as stasis]
   [optimus.export]
   [optimus.optimizations :as optimizations]
   [taoensso.timbre :as log]
   [upwebsite.web :as web]))

(def export-dir "dist")

(defn export! [& [ctx-path]]
  (if ctx-path (reset! web/context-path ctx-path))
  (log/debug "context-path: " @web/context-path)
  (let [assets (optimizations/all (web/get-assets) {})]
    (stasis/empty-directory! export-dir)
    (optimus.export/save-assets assets export-dir)
    (stasis/export-pages (web/get-pages) export-dir {:optimus-assets assets})))

(def -main export!)
