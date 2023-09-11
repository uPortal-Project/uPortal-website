(ns upwebsite.layout.index
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
        title "uPortal"
        heading (:title page-data)]
    (html5
     (default/layout-page-head request title)
     [:body
      (layout-header request context-path "uPortal")
      page
      (default/layout-footer request page)
      (default/layout-copyright request page)])))

(defn menu
  "Given a list of absolute, local hrefs, use the first as the menu heading (and link)
  and rest as menu items. Text will use the page-title fn."
  [request context-path hrefs]
  (let [path @context-path
        heading (first hrefs)
        menu-items (rest hrefs)]
    [:li.nav-item.nav-item-has-children
     [:a.page-scroll {:href (str path heading)} (last (url->title-parts heading))]
     [:ul.ud-submenu
      (for [menu-item menu-items]
        [:li.ud-submenu-item
         [:a.ud-submenu-link {:href (str path menu-item)} (last (url->title-parts menu-item))]])]]))

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
          (menu request context-path menu/features)
          (menu request context-path menu/support)
          (menu request context-path menu/community)
          (menu request context-path menu/events)
          (menu request context-path menu/about)
          [:li.nav-item
           [:a.fadeInUp.wow.btn.btn-common.btn-lg {:href (str @context-path "/support/deployment-guide.html")} "Try it out!"]]]]]]]
     [:div#main-slide.carousel.slide {:data-ride "carousel"}
      [:div.carousel-inner
       [:div.carousel-item.active {:style (str  "background-image: url(" (link/file-path request "/img/slider/banner4.jpg") ");")}
        [:div.carousel-caption
         [:div.container
          [:div.row
           [:div.col-lg-12
            [:h2.fadeInUp.wow {:data-wow-delay ".6s"} heading]
            [:h1.wow.fadeInDown.heading {:data-wow-delay ".4s"}
             "Designing for Student Engagement"]
            [:p.fadeInUp.wow {:data-wow-delay ".6s"} "A freely available solution to improve the experience of a student's journey through campus."]
            [:a.fadeInUp.wow.btn.btn-common.btn-lg
             {:data-wow-delay ".8s"
              :href "https://github.com/uPortal-Project/uPortal-start"}
             "Download"]]]]]]]]]))
