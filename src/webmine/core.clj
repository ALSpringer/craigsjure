(ns webmine.core
  (:require [clj-webdriver.taxi :refer :all])
  (:gen-class :main true))

(defstruct listing :title :price)

(defn -main
  [& args]
    (set-driver! {:browser :firefox} "http://harrisonburg.craigslist.com/bia/")
;    (print (first (map text (attribute (find-elements {:css ".row"}) :data-pid))))
    (print (first (map text (find-elements {:css ".row .pl a"}))))
    (print (first (map text (find-elements {:css ".row .price"})))))


