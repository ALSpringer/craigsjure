(ns webmine.core
  (:require [clj-webdriver.taxi :refer :all])
  (:gen-class :main true))
(use 'clojure.java.io)
(use '[clojure.string :only [split-lines]])

(defn spitlist
  [list]
  (doseq [i list]
    (spit "test.txt"
          (str (attribute i "data-pid") ",") :append true)
    (spit "test.txt"
          (str (text (find-element-under i {:css ".pl a"})) ","):append true)
    (spit "test.txt"
         (str (text (find-element-under i {:css "span span span"})) "\n"):append true))
    (print "Finished reading the top 10 bicycle entries"))

(defn readlist
  [file]
  (doseq [j (split-lines (slurp file))]
    (prn j)))

(defn -main
  [& args]
    (set-driver! {:browser :firefox} "http://boston.craigslist.com/bia/")
    (let [listings (find-elements {:css ".row"})]
      (spitlist (take 10 listings))))


