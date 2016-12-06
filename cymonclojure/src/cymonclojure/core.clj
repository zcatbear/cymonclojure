(ns cymonclojure.core
  (:gen-class)
  (:use [cymonclojure.cymon :as cymon])
  (:require [clojure.data.json :as json]))

(def token (clojure.string/trim (slurp "token.txt")))
(defn -main
  "This is meant to serve as a soft test for various things"
  [& args]
  (println token)
  (println (cymon/addToken token head))
  (println "Starting Tests")
  (println "\n\nDomain Blacklist")
  (json/pprint (cymon/domain_blacklist "malware" token))
  (println "\nIP Blacklist")
  (json/pprint (cymon/ip_blacklist "malware" token))
  (println "\nIP lookup (Googles IP)")
  (json/pprint (cymon/ip_lookup "8.8.8.8" token))
  (println "\nIP Events (Google's IP again")
  (json/pprint (cymon/ip_events "8.8.8.8" token))
  (println "\nIP Domains (Google")
  (json/pprint (cymon/ip_domains "8.8.8.8" token))
  (println "\nIP URLS (Google)")
  (json/pprint(cymon/ip_urls "8.8.8.8" token))
  (println "Well nothing failed yet soo... success?")
)
