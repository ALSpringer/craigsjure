(ns webmine.core
  (:require [clj-webdriver.taxi :refer :all])
  (:gen-class :main true))

(defn -main
  [& args]
    (set-driver! {:browser :firefox} "http://harrisonburg.craigslist.com/bia/")
    (print (second (map text (elements ".row")))))




