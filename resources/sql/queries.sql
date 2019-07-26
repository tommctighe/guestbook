-- :name get-events :? :*
-- :doc selects all events given user-selected filters, I imagine this is really slow for big lists
SELECT DISTINCT events.name, events.id, month, dates, date
FROM events, books, event_to_books
WHERE events.id=events.id
   --~ (if (seq (:region params)) " AND country IN (:v*:region)")
   --~ (if (seq (:month params)) " AND month IN (:v*:month)")
   --~ (if (seq (:book params)) " AND events.id = event_to_books.event_id AND event_to_books.book_id = books.id AND book_name IN (:v*:book)")
ORDER BY date

-- :name get-regions :? :*
-- :doc selects regions
SELECT DISTINCT country FROM events

-- :name get-books :? :*
-- :doc selects distinct books
SELECT book_name FROM books