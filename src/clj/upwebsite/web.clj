(ns upwebsite.web
  (:require [clojure.string :as str]
            [markdown.core :as md]
            [optimus.assets :as assets]
            [stasis.core :as stasis]
            [upwebsite.highlight :refer [highlight-code-blocks]]
            [upwebsite.layout.core :as layout]))

(defonce context-path (atom ""))

(defn get-assets
  "Pick up all files under resources/public/ for optimization"
  []
  (let [assets (assets/load-assets "public" [#".*"])]
    (map #(assoc % :context-path @context-path) assets)))

(defn partial-page-metadata
  "Given a url->page map entry, return a map of metadata for the page
  that includes the :url, :html-fragment, and :html-fn to render the page."
  [[url page]]
  (let [page-data {:url url :html-fragment page}]
    (assoc page-data
           :html-fn (fn [req] (layout/layout-page req page-data)))))

(defn partial-pages
  "Given a map of url->pages likely from resources/partials/, create a new map
  of url->(layout/layout-page req page) partial functions."
  [pages]
  (zipmap (keys pages)
          (map partial-page-metadata pages)))

(defn md->html [url]
  (str/replace url #"\.md$" ".html"))

(defn markdown-page-metadata
  "Given a url->page map entry, return a map of metadata for the page
  that includes the :url, :html-fragment, :markdown, and :html-fn to render the page."
  [[url page]]
  (let [html (md/md-to-html-string page)
        basic-data {:url (md->html url) :html-fragment html :markdown page}
        page-data (into basic-data (md/md-to-meta page))]
    (assoc page-data
           :html-fn (fn [req] (layout/layout-page req page-data)))))

(defn markdown-pages
  "Given a map of url->pages likely from resources/md/, create a new map
  of url->(layout/layout-page req page) partial functions,
  where url is renamed from .md to .html and Markdown is converted to html."
  [pages]
  (zipmap (map md->html (keys pages))
          (map markdown-page-metadata pages)))

(defn get-raw-pages
  "Merge the various types of pages into a single map of url->pages.
  Pages may be either strings (raw text) or a function (i.e. (layout-page)).
  Note: the map is used to label the type of pages for debugging/logging."
  []
  (stasis/merge-page-sources
   {:public (stasis/slurp-directory "resources/public" #".*\.(html|css|js)$")
    :partials (partial-pages (stasis/slurp-directory "resources/partials" #".*\.html$"))
    :markdown (markdown-pages (stasis/slurp-directory "resources/md" #".*\.md$"))}))

(defn prepare-page
  "From a url:page pair and request, create the page (if not a string) and post-process."
  [[url page] req]
  (let [prep-page (if (string? page)      ;; if string, its basic text file
                    page
                    ((:html-fn page) req))] ;; else gen the html, probably layout-page fn
    (if (str/ends-with? url ".html")
      (highlight-code-blocks prep-page)
      prep-page)))

(defn prepare-pages
  "Create a map of url->(pepare-page page) function from map of url->raw pages."
  [pages]
  (zipmap (keys pages)
          (map #(partial prepare-page %) pages)))

(defn get-pages
  "Main function to generate a map of url->page fn (without optimizations)
  for use by either http server or export code."
  []
  (prepare-pages (get-raw-pages)))
