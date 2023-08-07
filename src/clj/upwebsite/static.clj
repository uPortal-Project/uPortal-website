(ns upwebsite.static
  (:require
   [stasis.core :as stasis]
   [optimus.export]
   [optimus.optimizations :as optimizations]
   [upwebsite.web :as web]))

(def export-dir "dist")

(defn export! []
  (let [assets (optimizations/all (web/get-assets) {})]
    (stasis/empty-directory! export-dir)
    (optimus.export/save-assets assets export-dir)
    (stasis/export-pages (web/get-pages) export-dir {:optimus-assets assets})))

(def -main export!)
