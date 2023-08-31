(ns upwebsite.page-title
  "Functions to calculate page titles."
  (:require [clojure.string :as s]))

(defn- url->title
  "Calculate title string based on url"
  [url]
  (let [parts (s/split url #"/|\.")]
    (->> parts
         next
         drop-last
         (map s/capitalize)
         (s/join " - "))))

(defn ->title
  "Calculate title string based on page metadata."
  [page-data]
  (condp apply [page-data]
    :title (:title page-data)
    :url   (url->title (:url page-data))
    "uPortal"))
