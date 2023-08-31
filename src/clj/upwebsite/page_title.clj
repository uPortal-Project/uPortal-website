(ns upwebsite.page-title
  "Functions to calculate page titles."
  (:require [clojure.string :as s]))

(defn- capitalize-words
  "fn from the clojuredocs.org website for capitalize fn.
  Capitalize every word in a string"
  [words]
  (->> (s/split (str words) #"\b")
       (map s/capitalize)
       s/join))

(defn- url->title
  "Calculate title string based on url"
  [url]
  (let [parts (-> url
                  (s/replace #"-" " ")
                  (s/split #"/|\."))]
    (->> parts
         next
         drop-last
         (map capitalize-words)
         (s/join " - "))))

(defn ->title
  "Calculate title string based on page metadata."
  [page-data]
  (condp apply [page-data]
    :title (:title page-data)
    :url   (url->title (:url page-data))
    "uPortal"))
