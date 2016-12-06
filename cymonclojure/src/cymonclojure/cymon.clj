(ns cymonclojure.cymon
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def baseUrl "https://cymon.io/api/nexus/v1")

(def head {:headers 
           {:content-type "application/json" 
            :accept "application/json" }})

(defn addToken [token header] 
  (assoc
    (get header :headers) 
    :Authorization 
    (str "Token " token)))

(defn domain_blacklist [tag token] 
  (let 
    [x 
     (client/get 
       (str baseUrl "/blacklist/domain/" tag "/?days=1&limit=10&offset=0") 
       (addToken token head))] 
    (when (= (x :status) 200) ; Only return if success
      (json/read-str (x :body)))))

(defn ip_blacklist [tag token]
  (let 
    [x
    (client/get 
      (str baseUrl "/blacklist/ip/" tag "/?days=1&limit=10*offset=0")
      (addToken token head))]
    (when (= (x :status) 200)
      (json/read-str (x :body)))))

(defn ip_lookup [ip token] 
  (let 
    [x 
     (client/get 
       (str baseUrl "/ip/" ip)
       (addToken token head))]
    (when (= (x :status) 200)
      (json/read-str (x :body)))))

(defn ip_events [ip token]
  (let 
    [x
     (client/get
      (str baseUrl "/ip/" ip "/events")
       (addToken token head))]
  (when (= (x :status) 200)
    (json/read-str (x :body)))))

(defn ip_domains [ip token] 
  (let 
    [x
     (client/get
      (str baseUrl "/ip/" ip "/domains")
      (addToken token head))]
    (when (= (x :status) 200)
      (json/read-str (x :body)))))

(defn ip_urls [ip token]
  (let [x
   (client/get
    (str baseUrl "/ip/" ip "/urls")
    (addToken token head))]
    (when (= (x :status) 200)
    (json/read-str (x :body)))))






