(ns upwebsite.static
  (:require
   [stasis.core :as stasis]
   [optimus.export :as ex]
   [optimus.optimizations :as optimizations]
   [taoensso.timbre :as log]
   [upwebsite.web :as web]))

(def export-dir "dist")

(defn save-assets
  "Replace optimus save-assets fn to ignore context-path when saving assets."
  [assets target-dir]
  (doseq [asset assets]
    (let [path (str target-dir (:path asset))]
      (#'ex/create-folders path)
      (#'ex/save-asset-to-path asset path))))

(defn export! [& [ctx-path]]
  (if ctx-path (reset! web/context-path ctx-path))
  (log/debug "context-path: " @web/context-path)
  (let [assets (optimizations/all (web/get-assets) {})]
    (stasis/empty-directory! export-dir)
    (save-assets assets export-dir)
    (stasis/export-pages (web/get-pages) export-dir {:optimus-assets assets})))

(def -main export!)
