(ns upwebsite.highlight
  "Highlight code blocks with pygments CSS."
  (:require [clojure.java.io :as io]
            [clygments.core :as pygments]
            [net.cgrand.enlive-html :as enlive]))

(defn- extract-code
  "The pygments/highlight fn returns a <pre><code> html string that needs
  to have the surrounding <pre> tag stripped. Convert said string back into
  a <code> node."
  [highlighted]
  (-> highlighted            ;; take the html string
      java.io.StringReader.  ;; wrap it in a Java Reader
      enlive/html-resource   ;; parse it into Enlive data
      (enlive/select [:pre]) ;; get the <pre> node
      first                  ;; get the first node inside, should be <code>
      :content))             ;; return it's content

(defn- highlight
  "Given a <code> node inside a <pre> node, return a new node
  with pygment coloring for the language."
  [node]
  (let [code (->> node :content (apply str))    ;; code = <code> as string from <pre>
        lang (->> node :attrs :class keyword)]  ;; lang = language from the class attr
    (assoc node :content (-> code
                             (pygments/highlight lang :html)
                             extract-code))))

(defn highlight-code-blocks
  "Given HTML, return new HTML string, replacing <pre><code> nodes
  with return of highlight function,
  and add 'highlight' as a class to <code> nodes."
  [page]
  (enlive/sniptest page
                   [:pre :code] highlight
                   [:pre :code] #(assoc-in % [:attrs :class] "highlight")))
