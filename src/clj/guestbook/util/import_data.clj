(ns guestbook.util.import-data
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.string :as str]
    [clojure.spec.alpha :as s])
  (:import [java.time LocalDate Month]
           [java.time.format DateTimeFormatter]))

;; I think there's likely a better way to convert from month string to int
(def month->int {"jan" 1
                 "feb" 2
                 "mar" 3
                 "apr" 4
                 "may" 5
                 "jun" 6
                 "jul" 7
                 "aug" 8
                 "sep" 9
                 "oct" 10
                 "nov" 11
                 "dec" 12})

;; TODO consider importing using sql CSV import. Will need to convert from varchar. May need to adapt this code
;; to clean up the existing data.

(s/def ::event (s/tuple ::year ::month ::weekend ::days-of-week ::days-of-month ::times ::holiday ::title ::times
                        ::location ::plus ::book ::directions ::formula ::info))

(defn format-sql-insert-line
  [i row]
  (println row)
  (let [[year month weekend days-of-week days-of-month times holiday title times location plus book directions formula info] row
        days-range (str/split days-of-month #"-|,")
        day-int    (Integer/parseInt (first days-range))
        month-int  (get month->int (str/lower-case month))
        year-int   (Integer/parseInt year)
        date       (LocalDate/of year-int month-int day-int)
        date-str   (.format (DateTimeFormatter/ofPattern "YYYY-MM-dd") date)]
    (format "(%s,DATE '%s',%s,'%s','%s','%s','%s','%s','%s','%s','%s'"
            i
            date-str
            year-int
            month
            days-of-week
            days-of-month
            title
            times
            location
            info
            "Region X"))) ;TODO can we programatically assign region?

(defn create-sql-inserts
  []

  (with-open [reader (io/reader "./raw-input-data/MinutesBookDirectoryAnnual2019.csv")]
    (doall (->> (csv/read-csv reader)
                rest
                (take 20)
                (filter #(= 15 (count %)))
                (map-indexed format-sql-insert-line)))))
                ;(map prn)))))

; (id,event_id,book_id)
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





