(ns upwebsite.web
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [markdown.core :as md]
            ;;[me.raynes.cegdown :as md]
            [optimus.assets :as assets]
            [optimus.optimizations :as optimizations]
            [optimus.prime :as optimus]
            [optimus.strategies :refer [serve-live-assets]]
            [stasis.core :as stasis]
            [upwebsite.highlight :refer [highlight-code-blocks]]
            [upwebsite.layout :as layout]))

(defn get-assets []
  (assets/load-assets "public" [#".*"]))

(def pegdown-options ;; https://github.com/sirthias/pegdown
  [:autolinks :fenced-code-blocks :strikethrough])

(defn partial-pages [pages]
  (zipmap (keys pages)
          (map #(fn [req] (layout/layout-page req %)) (vals pages))))

(defn markdown-pages [pages]
  (zipmap (map #(str/replace % #"\.md$" ".html") (keys pages))
          (map #(fn [req] (layout/layout-page req (md/md-to-html-string %)))
               (vals pages))))

(defn get-raw-pages []
  (stasis/merge-page-sources
   {:public (stasis/slurp-directory "resources/public" #".*\.(html|css|js)$")
    :partials (partial-pages (stasis/slurp-directory "resources/partials" #".*\.html$"))
    :markdown (markdown-pages (stasis/slurp-directory "resources/md" #".*\.md$"))}))

(defn prepare-page
  "From a URL:page pair and request, create the page (if not a string) and post-process."
  [[url page] req]
  (let [prep-page (if (string? page) page (page req))] ;; page is string or fn
    (if (str/ends-with? url ".html")
      (highlight-code-blocks prep-page)
      prep-page)))

(defn prepare-pages
  "Create a map of URLs -> (pepare-page page) functions from map of URL -> raw pages."
  [pages]
  (zipmap (keys pages)
          (map #(partial prepare-page %) pages)))

(defn get-pages []
  (prepare-pages (get-raw-pages)))

(def app (-> (stasis/serve-pages get-pages)
             (optimus/wrap get-assets optimizations/all serve-live-assets)))

(def dev-app app)

(def prod-app app)
