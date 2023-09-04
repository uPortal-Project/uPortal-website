(ns upwebsite.layout.sample
  "Sample layout for for inspiration."
  (:require [clojure.string :as s]
            [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [upwebsite.page-title :refer [url->title-parts]]
            [upwebsite.layout.menu :as menu]
            [upwebsite.layout.default :as default]))

;; This is a sample layout that leverages some fns of the default.
;; You can use as much or little of the default layout.
;;
;; In this sample, we might change the <head> content and the heading of the page.

(declare layout-page-head)
(declare layout-header)

(defn layout-page ^String [request context-path page-data]
  (let [page (:html-fragment page-data)
        title (str "uPortal: " (:title page-data))
        heading (:title page-data)]
    (html5
     (layout-page-head request title)
     [:body
      (layout-header request context-path heading)
      page
      (default/layout-footer request page)
      (default/layout-copyright request page)])))

(defn layout-page-head [request title]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0"}]
   [:title title]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/main.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/line-icons.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/animate.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/bootstrap.min.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/nivo-lightbox.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/responsive.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/slicknav.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/pygments-css/autumn.css")}]])

(defn layout-header [request context-path heading]
  (let [banner (rand-nth default/banners)]
    [:header#header-wrap
     [:div.navigation
      [:div.container
       [:div.navbar.navbar-expand-lg
        [:a.navbar-brand {:href "index.html"}
         [:img {:src (link/file-path request "/img/logo/uportal-logo-white.png") :alt "Logo"}]]
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
           [:a.page-scroll {:href "/index.html"} "Home"]]
          (default/menu request context-path menu/features)
          (default/menu request context-path menu/support)
          (default/menu request context-path menu/community)
          (default/menu request context-path menu/events)
          (default/menu request context-path menu/about)
          [:li.nav-item
           [:a.fadeInUp.wow.btn.btn-common.btn-lg {:href (str @context-path "/support/deployment-guide.html")} "Try it out!"]]]]]]]
     [:div#main-slide.carousel.slide {:data-ride "carousel"}
      [:div.carousel-inner
       [:div.carousel-item.subpage.active {:style (str  "background-image: url(" (link/file-path request (:href banner)) ");")}
        [:div.carousel-caption
         [:div.container
          [:div.row
           [:div.col-lg-12
            [:h1.wow.fadeInDown.heading.subpage {:data-wow-delay ".4s" :style (str "color: " (:h-color banner))}
             heading]]]]]]]]]))
