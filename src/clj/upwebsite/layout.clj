(ns upwebsite.layout
  (:require [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [stasis.core :as stasis]))

(declare layout-page-head)
(declare layout-header)
(declare layout-footer)
(declare layout-copyright)

(defn layout-page [request page]
  (html5
    (layout-page-head request page)
    [:body
     (layout-header request page)
     page
     (layout-footer request page)
     (layout-copyright request page)]))

(defn layout-page-head [request page]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0"}]
   [:title "uPortal"]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/main.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/line-icons.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/animate.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/bootstrap.min.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/nivo-lightbox.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/responsive.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/slicknav.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/pygments-css/autumn.css")}]
   ])

(defn layout-header [request page]
  [:header#header-wrap
   [:div.navigation
    [:div.container
     [:div.navbar.navbar-expand-lg
      [:a.navbar-brand {:href "index.html"}
       [:img {:src "/img/logo/uportal-logo-white.png" :alt "Logo"}]]
      [:button.navbar-toggler
       {:type "button"
        :data-toggle "collapse"
        :data-target "#navbarSupportedContent"
        :aria-controls "navbarSupportedContent"
        :aria-expanded "false"
        :arial-label "Toggle navigation"}
       [:span.toggler-icon]
       [:span.toggler-icon]
       [:span.toggler-icon]]
      [:div#navbarSupportedContent.collapse.navbar-collapse
       [:ul.navbar-nav.ml-auto
        [:li.nav-item.active
         [:a.page-scroll {:href "./index.html"} "Home"]]
        [:li.nav-item
         [:a.page-scroll {:href "./community.html"} "Community"]]
        [:li.nav-item
         [:a.page-scroll {:href "./about.html"} "About"]]
        [:li.nav-item
         [:a.page-scroll {:href "https://github.com/uPortal-Project/uPortal-start"} "Download"]]]]]]]
   [:div#main-slide.carousel.slide {:data-ride "carousel"}
    [:div.carousel-inner
     [:div.carousel-item.active {:style "background-image: url(/img/slider/banner5.jpg);"}
      [:div.carousel-caption
       [:div.container
        [:div.row
         [:div.col-lg-12
          [:h1.wow.fadeInDown.heading {:data-wow-delay ".4s"}
           "Designing for Student Engagement"]]]]]]]]])

(defn layout-footer [request page]
  (let [footer-col-classes  "col-md-6 col-lg-3 col-sm-6 col-xs-12 wow fadeInUp"] ;; repeated in 3 divs
    [:footer.footer-area.section-padding
     [:div.container
      [:div.row
       [:div {:class footer-col-classes :data-wow-delay "0.2s"}
        [:h3 [:img {:src "img/logo/uportal-logo-white.png" :alt ""}]]
        [:p
         "uPortal is the leading open-source enterprise portal framework built by and for higher education institutions, K-12 schools, and research communities."]
        ]
       [:div {:class footer-col-classes :data-wow-delay "0.4s"}
        [:h3 "QUICK LINKS"]
        [:ul
         [:li
          [:a {:href "#"} "About Us"]]]]
       [:div {:class footer-col-classes :data-wow-delay "0.8s"}
        [:h3 "FOLLOW US ON"]
        [:ul
         [:li
          [:a.twitter {:href "https://twitter.com/uPortal"}
           [:i.lni-twitter-filled]]]]]]]]))

(defn layout-copyright [request page]
  [:div#copyright
   [:div.container
    [:div.row
     [:div.col-md-12
      [:div.site-info
       [:p "© Designed and Developed by" [:a {:href "http://uideck.com" :rel "nofollow"} "UIdeck"]
        ]]]]]])
