
-- :name get-events :? :*
-- :doc selects all events given user-selected filters, I imagine this is really slow for big lists
SELECT * FROM events WHERE id=id
/*~

(defn get-matching-keys [substring the-map]
  (let [ss (re-pattern substring)]
    (map #(str "'" (the-map %) "'") (filter #(re-find ss (name %)) (keys the-map)))))

(defn create-sql-clause [options user-choices]
  (for [opt options
        :let [items (get-matching-keys opt user-choices)]
        :when (not-empty items)]
    (str " AND event_" opt " IN (" (clojure.string/join "," items) ")")))

;;(def my-params {:region1 "al" :region2 "uk" :book1 "denson"})
(clojure.string/join (create-sql-clause ["book" "region" "month"] params))

~*/

-- :name get-regions :? :*
-- :doc selects distinct regions
SELECT DISTINCT event_region FROM events

-- :name get-months :? :*
-- :doc selects distinct months
SELECT DISTINCT event_month FROM events

-- :name get-books :? :*
-- :doc selects distinct books
SELECT DISTINCT event_book FROM events