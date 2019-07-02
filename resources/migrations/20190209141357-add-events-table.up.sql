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

INSERT INTO event_to_books_new (id,event_id,book_id)
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

CREATE TABLE EVENTS
(id INTEGER PRIMARY KEY,
 full_date DATE,
 year INTEGER,
 month VARCHAR(30),
 days VARCHAR(30),
 dates VARCHAR(30),
 title VARCHAR(200),
 location VARCHAR(200),
 times VARCHAR(100),
 info VARCHAR(200),
 region VARCHAR(100),
 timestamp TIMESTAMP);

 --;;

INSERT INTO events(id, full_date, year, month, days, dates, title, times, location, info, region)
    VALUES
(2,DATE '2019-01-01',2019,'january','tuesday','1','Dominic Ciavonne Ziegler Memorial Singing','9-3:30 p.m.','Old Felta Schoolhouse, Healdsburg, California','Lindy Groening', 'U.S. West'),
(1,DATE '2019-01-01',2019,'january','tuesday','1','New Year''s Day Singing','11:15 a.m.-4:00 p.m.','Mapperley Parish Church, Nr Ilkeston, Derbyshire, United Kingdom','Helen Brown', 'UK'),
(4,DATE '2019-03-09',2019,'march','saturday','9','Smith Memorial Singing','10:00 a.m.','New Harmony Community Center, Smith County, Texas', 'Gaylon Powell', 'U.S. South'),
(5,DATE '2019-04-27',2019,'april','saturday and sunday','27-28','Rusk County Convention','10:00 a.m.','Old Pine Grove Church, Henderson, Texas','Robert Vaughn or Gaylon Powell', 'U.S. South'),
(6,DATE '2019-05-11',2019,'may','saturday','11','Boston Singing','10:00 a.m.','St. Paul''s Episcopal, 15 St. Paul St., Brookline, Massachusetts', 'www.bostonsacredharp.org', 'U.S. East'),
(7,DATE '2019-06-01',2019,'june','saturday and sunday','1-2','Oslo Sacred Harp Singing','Saturday 10:30 a.m.-4:00 p.m. Sunday noon-3:30 p.m.','Uranienborgveien 2 (Yellow house behind the castle), Oslo, Norway','Camilla Marie Widholm or Oskar Kvasnes', 'Europe'),
(8,DATE '2019-07-19',2019,'july','friday and saturday','19 and 20','Quebec Convention','Friday evening 7-9 p.m. and Saturday 10:00 a.m.','Friday,  St. Georges Anglican Church, Lennoxville, Quebec. Saturday, Ways Mills Community Hall, Barnston-Quest, Quebec','Chuck Neville', 'Canada, East'),
(9,DATE '2019-08-18',2019,'august','sunday','18','Cleburne County Convention','9:30 a.m.','Edwardsville Baptist Church, Edwardsville, Cleburne County, Alabama', '', 'U.S. South'),
(10,DATE '2019-09-21',2019,'september','saturday','21','West Florida Convention','','Union Hill Singing Hall','Aubrey Barfield, taubrey1@cox.net', 'U.S. South'),
(11,DATE '2019-10-19',2019,'october','saturday and sunday','19-20','Pacific Northwest Convention, Oregon','9:00 a.m.-3:00 p.m.','Laurelhurst Club','poshchair@gmail.com', 'U.S. West'),
(12,DATE '2019-11-16',2019,'november','saturday','16','Georgian Harmony Singing','10:00 a.m.','Hollingsworth Home, 1547 Adams Clark Rd, Commerce, Georgia 30530','John Plunkett or Oscar McGuire', 'U.S. South'),
(3,DATE '2019-12-30',2019,'december','saturday', '30', 'Lehigh Valley All-Day Singing', '10:00 a.m.', 'Bethlehem, PA, location TBA', 'Daniel Hunter', 'U.S. East');

--;;

INSERT INTO event_to_books (id,event_id,book_id)
VALUES
(1, 1, 3),
(2, 2, 3),
(3, 3, 5),
(4, 4, 3),
(5, 4, 1),
(6, 5, 2),
(7, 5, 1),
(8, 6, 3),
(9, 7, 3),
(10, 8, 3),
(11, 9, 3),
(12, 10, 1),
(13, 11, 2),
(14, 11, 3),
(15, 12, 4);