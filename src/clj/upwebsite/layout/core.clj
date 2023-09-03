(ns upwebsite.layout.core
  "Layout logic to determine layout and other details."
  (:require [upwebsite.page-title :refer (->title)]
            [upwebsite.layout.default :as default]))

(defn layout-page ^String [request context-path page-data]
  (let [more-data (assoc page-data
                         :title (->title page-data))]
    (default/layout-page request context-path more-data)))
