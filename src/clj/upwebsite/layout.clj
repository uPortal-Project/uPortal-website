(ns upwebsite.layout
  (:require [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [stasis.core :as stasis]))

(declare layout-page-head)
(declare layout-page-header)
(declare layout-page-footer)

(defn layout-page [request page]
  (html5
    (layout-page-head request page)
    [:body
     (layout-page-header request page)
     page
     (layout-page-footer request page)]))

(defn layout-page-head [request page]
  (html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1.0"}]
     [:title "uPortal"]
     [:link {:rel "stylesheet" :href (link/file-path request "/styles/main.css")}]
     [:link {:rel "stylesheet" :href (link/file-path request "/styles/animate.css")}]
     [:link {:rel "stylesheet" :href (link/file-path request "/styles/bootstrap.min.css")}]
     [:link {:rel "stylesheet" :href (link/file-path request "/styles/nivo-lightbox.css")}]
     [:link {:rel "stylesheet" :href (link/file-path request "/styles/responsive.css")}]
     [:link {:rel "stylesheet" :href (link/file-path request "/styles/slicknav.css")}]
     [:link {:rel "stylesheet" :href (link/file-path request "/pygments-css/autumn.css")}]
     ]))

(defn layout-page-header [request page]
  )

(defn layout-page-footer [request page])
