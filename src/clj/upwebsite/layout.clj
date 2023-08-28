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
   [:link {:rel "stylesheet" :href (link/file-path request "./styles/main.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "./styles/line-icons.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "./styles/animate.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "./styles/bootstrap.min.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "./styles/nivo-lightbox.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "./styles/responsive.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "./styles/slicknav.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "./pygments-css/autumn.css")}]])

(defn layout-header [request page]
  [:header#header-wrap
   [:div.navigation
    [:div.container
     [:div.navbar.navbar-expand-lg
      [:a.navbar-brand {:href "index.html"}
       [:img {:src "./img/logo/uportal-logo-white.png" :alt "Logo"}]]
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
        [:li.nav-item.nav-item-has-children 
         [:a.page-scroll.subpage {:href "./about.html"} "About"]
		  [:ul.ud-submenu
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./apereo.html"} "Apereo Foundation"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./governance.html"} "Governance"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./partners.html"} "Partners"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./news.html"} "News"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./privacy.html"} "Data Privacy Policy"]]]]
        [:li.nav-item.nav-item-has-children 
         [:a.page-scroll.subpage {:href "./support.html"} "Support"]
		  [:ul.ud-submenu
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./userdocs.html"} "User Guide"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./admindocs.html"} "Admin Guide"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./devdocs.html"} "Developer Guide"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./deploydocs.html"} "Deployment Guide"]]]]
        [:li.nav-item.nav-item-has-children 
         [:a.page-scroll.subpage {:href "./community.html"} "Community"]
		  [:ul.ud-submenu
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./roadmap.html"} "Roadmap"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./lists.html"} "Mailing List"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./conduct.html"} "Code of Conduct"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./committers.html"} "Committers"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./onboarding.html"} "Contributor Onboarding"]]]]
        [:li.nav-item.nav-item-has-children 
         [:a.page-scroll.subpage {:href "./casestudies.html"} "Case Studies"]
		  [:ul.ud-submenu
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./casestudies.html"} "Case Studies"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./showcases.html"} "Showcases"]]]]
        [:li.nav-item.nav-item-has-children 
         [:a.page-scroll.subpage {:href "./events.html"} "Events"]
		  [:ul.ud-submenu
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./devdays.html"} "Dev Days"]]
		   [:li.ud-submenu-item
		    [:a.ud-submenu-link {:href "./calls.html"} "Monthly Calls"]]]]]]]]]
   [:div#main-slide.carousel.slide {:data-ride "carousel"}
    [:div.carousel-inner
     [:div.carousel-item.subpage.active {:style "background-image: url(/img/slider/banner2.jpg);"}
      [:div.carousel-caption
       [:div.container
        [:div.row
         [:div.col-lg-12
          [:h1.wow.fadeInDown.heading.subpage {:data-wow-delay ".4s"}
           ""]]]]]]]]])

(defn layout-footer [request page]
  (let [footer-col-classes  "col-md-6 col-lg-3 col-sm-6 col-xs-12 wow fadeInUp"] ;; repeated in 3 divs
    [:footer.footer-area.section-padding
     [:div.container
      [:div.row
       [:div {:class footer-col-classes :data-wow-delay "0.2s"}
        [:h3 [:img {:src "./img/logo/uportal-logo-white.png" :alt ""}]]
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
