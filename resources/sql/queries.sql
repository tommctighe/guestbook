-- :name get-events :? :*
-- :doc selects all events given user-selected filters, I imagine this is really slow for big lists
SELECT DISTINCT events.id, event_region, event_month, event_title, books.book_name
FROM events, books, event_to_books
WHERE events.id=events.id
   --~ (if (seq (:region params)) " AND event_region IN (:v*:region)")
   --~ (if (seq (:month params)) " AND event_month IN (:v*:month)")
   --~ (if (seq (:book params)) " AND events.id = event_to_books.event_id AND event_to_books.book_id = books.id AND book_name IN (:v*:book)")
GROUP BY event_title, books.book_name

-- :name get-regions :? :*
-- :doc selects regions
SELECT DISTINCT event_region FROM events

-- :name get-books :? :*
-- :doc selects distinct books
SELECT book_name FROM books