(ns guestbook.util.import-data
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.string :as str]
    [clojure.spec.alpha :as s]))

(defn parse-comma-delimited-ints
  "takes a string representation of a comma-delimited list of integers
  and returns a vector of those integers as clojure ints
  Example:
  (parse-book-id-list \"1,4,6\")
   => [1 4 6]"
  [book-id-list]
  (let [str-id-coll (-> book-id-list
                        (str/replace #" " "")
                        (str/split #","))]
    (mapv #(Integer/parseInt %) str-id-coll)))

(comment
  ;; Generate event-id/book-id pivot table
  (with-open [reader (io/reader "./raw-input-data/revised-annual-singings-no-blank-rows.csv")]
    (let [rows (->> (csv/read-csv reader)
                    rest
                    (map (fn [coll] {:event-id (nth coll 0)
                                     :book-ids (parse-comma-delimited-ints (nth coll 15))}))
                    (mapcat (fn [m]
                              (for [book-id (:book-ids m)]
                                {:event-id (:event-id m)
                                 :book-id book-id})))
                    (map-indexed (fn [i m]
                                   (assoc m :pivot-id i)))
                    (map (fn [{:keys [pivot-id event-id book-id]}]
                           (format "(%s, %s, %s)"
                                   pivot-id
                                   event-id
                                   book-id))))]
      (str (str/join ",\n" rows) ";"))))





