(ns upwebsite.layout.core
  "Layout logic to determine layout and other details."
  (:require [upwebsite.page-title :refer (->title)]
            [upwebsite.layout.default :as default]
            [upwebsite.layout.sample :as sample]))

(defn layout-page ^String [request context-path page-data]
  (let [more-data (assoc page-data
                         :title (->title page-data))
        layout-fn (condp re-find (:url more-data)           ;; regex on url used to pick layout (for now)
                    #".*/index.html$" default/layout-page   ;; index.html pages use default layout
                    #"/sample.html$" sample/layout-page     ;; example of sample
                    default/layout-page)]                   ;; default value if no matches
    (layout-fn request context-path more-data)))
