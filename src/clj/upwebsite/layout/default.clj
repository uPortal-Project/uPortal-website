(ns upwebsite.layout.default
  "Default layout for most subpages."
  (:require [clojure.string :as s]
            [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [upwebsite.page-title :refer [url->title-parts]]
            [upwebsite.layout.menu :as menu]))

;; could have been just the number, but this gives us more flexibility
(def banners [{:href "/img/slider/banner1.jpg" :h-color "purple"}
              {:href "/img/slider/banner2.jpg" :h-color "purple"}
              {:href "/img/slider/banner3.jpg" :h-color "purple"}
              {:href "/img/slider/banner4.jpg" :h-color "purple"}
              {:href "/img/slider/banner5.jpg" :h-color "purple"}
              {:href "/img/slider/banner6.jpg" :h-color "purple"}])

(declare layout-page-head)
(declare layout-header)
(declare layout-footer)
(declare layout-copyright)

(defn layout-page ^String [request context-path page-data]
  (let [page (:html-fragment page-data)
        title (str "uPortal: " (:title page-data))
        heading (:title page-data)]
    (html5
     (layout-page-head request title)
     [:body
      (layout-header request context-path heading)
      page
      (layout-footer request page)
      (layout-copyright request page)])))

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

(defn menu
  "Given a list of absolute, local hrefs, use the first as the menu heading (and link)
  and rest as menu items. Text will use the page-title fn."
  [request context-path hrefs]
  (let [path @context-path
        heading (first hrefs)
        menu-items (rest hrefs)]
    [:li.nav-item.nav-item-has-children
     [:a.page-scroll.subpage {:href (str path heading)} (last (url->title-parts heading))]
     [:ul.ud-submenu
      (for [menu-item menu-items]
        [:li.ud-submenu-item
         [:a.ud-submenu-link {:href (str path menu-item)} (last (url->title-parts menu-item))]])]]))

(defn layout-header [request context-path heading]
  (let [banner (rand-nth banners)]
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
           [:a.page-scroll {:href (str @context-path "/index.html")} "Home"]]
          (menu request context-path menu/features)
          (menu request context-path menu/support)
          (menu request context-path menu/community)
          (menu request context-path menu/events)
          (menu request context-path menu/about)
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

(defn layout-footer [request page]
  (let [footer-col-classes  "col-md-6 col-lg-3 col-sm-6 col-xs-12 wow fadeInUp"] ;; repeated in 3 divs
    [:footer.footer-area.section-padding
     [:div.container
      [:div.row
       [:div {:class footer-col-classes :data-wow-delay "0.2s"}
        [:h3 [:img {:src (link/file-path request "/img/logo/uportal-logo-white.png") :alt ""}]]
        [:p
         "uPortal is the leading open-source enterprise portal framework built by and for higher education institutions, K-12 schools, and research communities."]]
       [:div {:class footer-col-classes :data-wow-delay "0.4s"}
        [:h3 "QUICK LINKS"]
        [:ul
         [:li
          [:a {:href "#"} "About Us"]]]]
       [:div {:class footer-col-classes :data-wow-delay "0.8s"}
        [:h3 "FOLLOW US ON"]
        [:ul.footer-social
         [:li
          [:a.twitter {:href "https://twitter.com/uPortal"}
           [:i.lni-twitter-filled]]]]]]]]))

(defn layout-copyright [request page]
  [:div#copyright
   [:div.container
    [:div.row
     [:div.col-md-12
      [:div.site-info
       [:p "© Designed and Developed by" [:a {:href "http://uideck.com" :rel "nofollow"} "UIdeck"]]]]]]])
