(ns upwebsite.layout.default
  "Default layout for most subpages."
  (:require [clojure.string :as s]
            [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [upwebsite.page-title :refer [url->title-parts]]
            [upwebsite.layout.menu :as menu]))

(declare layout-page-head)
(declare layout-header)
(declare layout-hero)
(declare layout-footer)

(defn layout-page ^String [request context-path page-data]
  (let [page (:html-fragment page-data)
        title (str "uPortal: " (:title page-data))
        heading (:title page-data)]
    (html5
     (layout-page-head request title)
     [:body
      (layout-header request context-path)
      ;; Markdown content is a raw HTML string inserted here.
      ;; The .content-area class in site.css restores proper styling for
      ;; standard HTML elements (h1-h6, p, ul, ol, a, blockquote, pre, table)
      ;; that markdown-clj generates. Without it, Bootstrap's reboot strips
      ;; list bullets, collapses margins, and flattens heading sizes.
      [:main.content-area
       [:div.container
        [:div.row
         [:div.col-lg-9.col-md-10.mx-auto
          page]]]]
      (layout-footer request context-path)
      [:script {:src (link/file-path request "/js/bootstrap.bundle.min.js")}]])))

(defn layout-page-head [request title]
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0"}]
   [:title title]
   [:link {:rel "preconnect" :href "https://fonts.googleapis.com"}]
   [:link {:rel "preconnect" :href "https://fonts.gstatic.com" :crossorigin "anonymous"}]
   [:link {:rel "stylesheet" :href "https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap"}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/bootstrap.min.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/bootstrap-icons.min.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/pygments-css/autumn.css")}]
   [:link {:rel "stylesheet" :href (link/file-path request "/styles/site.css")}]])

(defn menu
  "Given a list of absolute, local hrefs, use the first as the menu heading (and link)
  and rest as menu items. Text will use the page-title fn."
  [request context-path hrefs]
  (let [path @context-path
        heading (first hrefs)
        menu-items (rest hrefs)]
    [:li.nav-item.dropdown
     [:a.nav-link.dropdown-toggle {:href (str path heading)
                                   :role "button"
                                   :data-bs-toggle "dropdown"
                                   :aria-expanded "false"}
      (last (url->title-parts heading))]
     [:ul.dropdown-menu
      (for [menu-item menu-items]
        [:li
         [:a.dropdown-item {:href (str path menu-item)}
          (last (url->title-parts menu-item))]])]]))

(defn layout-header [request context-path]
  [:nav.navbar.navbar-expand-lg.navbar-dark.sticky-top
   {:style "background-color: var(--up-blue);"}
   [:div.container
    [:a.navbar-brand {:href (str @context-path "/index.html")}
     [:img {:src (link/file-path request "/img/logo/uportal-logo-white.png") :alt "uPortal" :height "32"}]]
    [:button.navbar-toggler
     {:type "button"
      :data-bs-toggle "collapse"
      :data-bs-target "#navbarNav"
      :aria-controls "navbarNav"
      :aria-expanded "false"
      :aria-label "Toggle navigation"}
     [:span.navbar-toggler-icon]]
    [:div#navbarNav.collapse.navbar-collapse
     [:ul.navbar-nav.ms-auto.align-items-center
      [:li.nav-item
       [:a.nav-link {:href (str @context-path "/index.html")} "Home"]]
      (menu request context-path menu/showcase)
      (menu request context-path menu/support)
      (menu request context-path menu/community)
      (menu request context-path menu/about)
      [:li.nav-item.ms-lg-2
       [:a.btn.btn-cta {:href (str @context-path "/support/deployment-guide.html")} "Try it out!"]]]]]])

(defn layout-hero [heading]
  [:section.page-hero
   [:div.container
    [:h1 heading]]])

(defn layout-footer [request context-path]
  [:footer.site-footer
   [:div.container
    [:div.row
     [:div.col-lg-4.col-md-6.mb-3
      [:h5 "uPortal"]
      [:p "The leading open-source enterprise portal framework built by and for higher education institutions, K-12 schools, and research communities."]]
     [:div.col-lg-2.col-md-6.mb-3
      [:h5 "Quick Links"]
      [:ul.list-unstyled
       [:li [:a {:href (str @context-path "/about/about.html")} "About"]]
       [:li [:a {:href (str @context-path "/community/community.html")} "Community"]]
       [:li [:a {:href (str @context-path "/support/support.html")} "Support"]]
       [:li [:a {:href (str @context-path "/showcase/showcase.html")} "Showcase"]]]]
     [:div.col-lg-3.col-md-6.mb-3
      [:h5 "Resources"]
      [:ul.list-unstyled
       [:li [:a {:href (str @context-path "/support/deployment-guide.html")} "Deployment Guide"]]
       [:li [:a {:href (str @context-path "/support/developer-guide.html")} "Developer Guide"]]
       [:li [:a {:href (str @context-path "/support/repositories.html")} "Repositories"]]]]
     [:div.col-lg-3.col-md-6.mb-3
      [:h5 "Connect"]
      [:div.footer-social
       [:a {:href "https://twitter.com/uPortal" :aria-label "Twitter"}
        [:i.bi.bi-twitter]]
       [:a {:href "https://github.com/uPortal-Project" :aria-label "GitHub"}
        [:i.bi.bi-github]]]]]
    [:div.footer-bottom.text-center
     [:p "uPortal is a project of the "
      [:a {:href "https://www.apereo.org"} "Apereo Foundation"]
      "."]]]])
