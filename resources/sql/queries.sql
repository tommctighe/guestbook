-- :name get-events :? :*
-- :doc selects all events given user-selected filters, I imagine this is really slow for big lists
SELECT * FROM events WHERE events.id=events.id
   --~ (if (seq (:region params)) " AND event_region IN ('Europe', 'UK')")

-- :name get-regions :? :*
-- :doc selects regions
SELECT DISTINCT event_region FROM events

-- :name get-books :? :*
-- :doc selects distinct books
SELECT book_name FROM books