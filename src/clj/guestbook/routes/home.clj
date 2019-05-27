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
        pair (seq opt)] ;;just want the val of each map!
    (if (some #{(second pair)} chosen)
      (assoc opt :checked "checked")
      (assoc opt :checked ""))))

(defn home-page [{:keys [flash]}]
  (layout/render
   "home.html"
   {:regions (db/get-regions)
    :books (db/get-books)}))

(defn show-events [{:keys [params]}]
  (let [chosen (vals params)]
    (layout/render
    "home.html"
    {:params params
     :regions (add-checked (db/get-regions) (:region params))
     :books (add-checked (db/get-books) (:book params))
     :events (db/get-events)
     })))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (POST "/" request (show-events request))
  (GET "/about" [] (about-page)))