(ns webmine.core
  (:require [clj-webdriver.taxi :refer :all])
  (:gen-class :main true)
  (:use [clojure.java.io]
  [clojure.core]
  [clojure.string :only [split-lines, split]]
  [clojure.set :only [difference]]))

(import '(java.io.file))

;output the top 10 entries from cl into a file for comparison
(defn spitlist
  [list]
  (spit "test.txt" "")
  (doseq [i list]
    (spit "test.txt"
          (str (attribute i "data-pid") ",") :append true)
    (spit "test.txt"
          (str (text (find-element-under i {:css ".pl a"})) ","):append true)
    (spit "test.txt"
         (str (text (find-element-under i {:css "span span span"})) "\n"):append true)))

;read the top 10 post-ids and return them
(defn readlist
  [file]
  (for [j (split-lines (slurp file))]
    (first (split j #","))))

;compare the current cl post-ids to the ones we have logged
;return the ones that do not match the previous top 10
(defn findnew
  [listings]
  (let [pidmap
        (zipmap
          (for [i (take 10 listings)] (attribute i "data-pid"))
          (take 10 listings))]
        (doseq [i (difference
          (set (keys pidmap))
          (set (readlist "test.txt")))]
          (seq (find pidmap i)))))

;first argument provided is the prefix
;second is the suffix (for now)
;ie. http://boston.craigslist.com/bia/
;prefix = boston, suffix = bia
(defn -main
  [& args]
    (set-driver! {:browser :firefox}
      (str "http://" (first args) ".craigslist.com/" (second args) "/"))
    (if-not (.exists (file "test.txt"))
      (spit "test.txt" ""))
    (let [listings (find-elements {:css ".row"})]
      (findnew listings)
      (spitlist (take 10 listings)))
    (quit))


