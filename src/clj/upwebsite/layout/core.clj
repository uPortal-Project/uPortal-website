(ns upwebsite.layout.core
  "Layout logic to determine layout and other details."
  (:require [upwebsite.layout.default :refer (default-layout-page)]))

(defn layout-page ^String [request page-data]
  (let [page (:html-fragment page-data)]
    (default-layout-page request page)))
