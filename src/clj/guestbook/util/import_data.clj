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
(comment
  (with-open [reader (io/reader "./raw-input-data/revised-annual-singings.csv")]

    (let [result (->> (csv/read-csv reader)
                      rest
                      (filter #(not (str/blank? (first %))))
                      (map #(nth % 14))
                      (map #(str/replace % #" " ""))
                      (map #(str/split % #","))
                      (map (fn [ids]
                             (mapv #(Integer/parseInt %) ids)))
                      (map-indexed (fn [i ids]
                                     [i ids]))
                      (mapcat (fn [[i ids]]
                                (map (fn [id] [i id]) ids)))
                      (map-indexed #(cons %1 %2))
                      (map (fn [[id event-id book-id]] (format "(%s, %s, %s)"
                                                               id
                                                               event-id
                                                               book-id)))
                      (str/join ",\n"))]
      (str result ";"))))


                ;rest
                ;(filter #(= 15 (count %)))
                ;(map-indexed format-sql-insert-line)))))
                ;(map prn)))))))




