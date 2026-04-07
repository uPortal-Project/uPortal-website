(ns upwebsite.layout.index
  "Homepage layout."
  (:require [hiccup.page :refer [html5]]
            [optimus.link :as link]
            [upwebsite.layout.default :as default]
            [upwebsite.layout.menu :as menu]))

(defn layout-page ^String [request context-path page-data]
  (let [page (:html-fragment page-data)]
    (html5
     (default/layout-page-head request "uPortal")
     [:body
      (default/layout-header request context-path)
      [:section.home-hero
       [:div.container
        [:div.row.align-items-center.g-5
         [:div.col-lg-6
          [:h1 "Designing for Student Engagement"]
          [:p.lead.mt-3 "A freely available, open-source solution to improve the experience of a student's journey through campus."]
          [:a.btn.btn-hero.mt-3
           {:href "https://github.com/uPortal-Project/uPortal-start"}
           [:i.bi.bi-download.me-2] "Get Started"]]
         [:div.col-lg-6.d-none.d-lg-block
          [:img.hero-img {:src (link/file-path request "/img/hero-campus.jpg")
                          :alt "Students collaborating on campus"}]]]]]
      ;; Homepage content sections (from resources/partials/index.html)
      page
      (default/layout-footer request context-path)
      [:script {:src (link/file-path request "/js/bootstrap.bundle.min.js")}]])))
