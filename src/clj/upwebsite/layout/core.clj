(ns upwebsite.layout.core
  "Layout logic to determine layout and other details."
  (:require [upwebsite.page-title :refer (->title)]
            [upwebsite.layout.default :refer (default-layout-page)]))

(defn layout-page ^String [request page-data]
  (let [more-data (assoc page-data
                         :title (->title page-data))]
    (default-layout-page request more-data)))
