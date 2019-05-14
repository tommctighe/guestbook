(ns guestbook.routes.home
  (:require [guestbook.layout :as layout]
            [guestbook.db.core :as db]
            [compojure.core :refer [defroutes GET]]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
            [struct.core :as st]))

(defn home-page [{:keys [flash]}]
  (layout/render
   "home.html"
   {:regions (db/get-regions)
    :months (db/get-months)
    :books (db/get-books)}))

(defn show-events [{:keys [params]}]
  (layout/render
    "home.html"
    {:regions (db/get-regions params)
     :months (db/get-months)
     :books (db/get-books)
     :events (db/get-events params)}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (POST "/" request (show-events request))
  (GET "/about" [] (about-page)))


