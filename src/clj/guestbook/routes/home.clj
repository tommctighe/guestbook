(ns guestbook.routes.home
  (:require [guestbook.layout :as layout]
            [guestbook.db.core :as db]
            [compojure.core :refer [defroutes GET]]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [struct.core :as st]))

(defn add-checked [options chosen]
  (for [opt options
        k-v (seq opt)]  ; just want the val of each map!
    (if (some #{(second k-v)} chosen)
      (assoc opt :checked "checked")
      (assoc opt :checked ""))))

(defn home-page [{:keys [flash]}]
  (layout/render
   "home.html"
   {:regions (db/get-regions)
    :books   (db/get-books)
    :events  (db/get-events)}))

(defn show-events [{:keys [params]}]
  (let [chosen (reduce-kv (fn [m k v]
                            (if (string? v)
                              (assoc m k [v])
                              (assoc m k v)))
                          {}
                          params)]
    (layout/render
     "home.html"
     {:params  chosen
      :regions (add-checked (db/get-regions) (:region chosen))
      :books   (add-checked (db/get-books) (:book chosen))
      :months  (reduce #(assoc %1 %2 "checked") {} (:month chosen))
      :events  (db/get-events chosen)})))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (POST "/" request (show-events request))
  (GET "/about" [] (about-page)))
