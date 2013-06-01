(ns webmine.core
  (:require [clj-webdriver.taxi :refer :all])
  (:gen-class :main true))
(use 'clojure.java.io)

(defn spitlist
  [list]
  (doseq [i list]
    (spit "test.txt"
          (str (attribute i "data-pid") " ") :append true)
    (spit "test.txt"
          (str (text (find-element-under i {:css ".pl a"})) " "):append true)
    (spit "test.txt"
         (str (text (find-element-under i {:css "span span span"})) "\n"):append true))
    (print "Finished reading the top 10 bicycle entries"))

(defn -main
  [& args]
    (set-driver! {:browser :firefox} "http://boston.craigslist.com/bia/")
    (let [listings (find-elements {:css ".row"})]
      (spitlist (take 10 listings))))
    ;(attribute (first (find-elements {:css ".row"}))"data-pid"))
    ;(print (first (map text (find-elements {:css ".row .pl a"}))))
    ;(print (first (map text (find-elements {:css ".row .price"})))))


