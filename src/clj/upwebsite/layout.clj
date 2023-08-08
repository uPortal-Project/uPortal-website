(ns upwebsite.layout
  (:require [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [stasis.core :as stasis]))

(defn layout-page [request page]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1.0"}]
    [:title "Tech blog"]
    [:link {:rel "stylesheet" :href "/styles/main.css"}]
    [:link {:rel "stylesheet" :href "/styles/animate.css"}]
    [:link {:rel "stylesheet" :href "/styles/bootstrap.min.css"}]
    [:link {:rel "stylesheet" :href "/styles/nivo-lightbox.css"}]
    [:link {:rel "stylesheet" :href "/styles/responsive.css"}]
    [:link {:rel "stylesheet" :href (link/file-path request "/styles/slicknav.css")}]
	[:link {:rel "stylesheet" :href (link/file-path request "/pygments-css/autumn.css")}]
	]
    [:body
     [:div.logo "cjohansen.no"]
     [:div.body page]]))
 