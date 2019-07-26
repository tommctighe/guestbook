CREATE TABLE BOOKS
(id INTEGER PRIMARY KEY,
 book_name VARCHAR(100));

 --;;

INSERT INTO books (id,book_name)
VALUES
(1, 'Cooper Book'),
(2, 'Christian Harmony'),
(3, 'Denson Book'),
(4, 'Georgian Harmony'),
(5, 'Shenandoah Harmony'),
(6, 'Colored Harmony'),
(7, 'Social Harp'),
(8, 'Southern Harmony'),
(9, 'J.L. White Book'),
(10, 'DH Mansfield American Vocalist'),
(11, 'Norumbega Harmony'),
(12, 'Missouri Harmony');

 --;;

CREATE TABLE EVENT_TO_BOOKS
(id INTEGER PRIMARY KEY,
 event_id INTEGER,
 book_id INTEGER);

 --;;

INSERT INTO event_to_books (id,event_id,book_id)
VALUES
(1, 1, 3),
(2, 2, 3),
(3, 3, 4),
(4, 4, 3),
(5, 5, 3),
(6, 6, 3),
(7, 7, 3),
(8, 8, 3),
(9, 9, 3),
(10, 10, 1),
(11, 11, 3),
(12, 12, 3),
(13, 13, 3),
(14, 14, 1),
(15, 15, 3),
(16, 16, 3),
(17, 17, 3),
(18, 18, 3),
(19, 19, 3),
(20, 19, 1),
(21, 19, 2),
(22, 19, 6),
(23, 20, 3),
(24, 21, 1),
(25, 22, 1),
(26, 23, 3),
(27, 24, 3),
(28, 25, 3),
(29, 26, 3),
(30, 27, 3),
(31, 28, 3),
(32, 29, 3),
(33, 30, 1),
(34, 31, 3),
(35, 32, 3),
(36, 33, 3),
(37, 34, 3),
(38, 35, 1),
(39, 36, 3);

 --;;

CREATE TABLE EVENTS AS SELECT * FROM CSVREAD('./raw-input-data/events.csv');

 --;;