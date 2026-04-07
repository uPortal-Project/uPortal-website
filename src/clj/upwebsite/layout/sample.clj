(ns upwebsite.layout.sample
  "Sample layout for inspiration. Delegates to default for most structure."
  (:require [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [upwebsite.layout.default :as default]
            [upwebsite.layout.menu :as menu]))

(defn layout-page ^String [request context-path page-data]
  (let [page (:html-fragment page-data)
        title (str "uPortal: " (:title page-data))
        heading (:title page-data)]
    (html5
     (default/layout-page-head request title)
     [:body
      (default/layout-header request context-path)
      [:main.content-area
       [:div.container
        [:div.row
         [:div.col-lg-9.col-md-10.mx-auto
          page]]]]
      (default/layout-footer request context-path)
      [:script {:src (link/file-path request "/js/bootstrap.bundle.min.js")}]])))
