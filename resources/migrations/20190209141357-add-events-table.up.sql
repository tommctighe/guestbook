CREATE TABLE EVENTS
(id INTEGER PRIMARY KEY,
 event_year INTEGER,
 event_month VARCHAR(30),
 event_days VARCHAR(30),
 event_dates VARCHAR(30),
 event_title VARCHAR(200),
 event_location VARCHAR(200),
 event_times VARCHAR(100),
 event_book VARCHAR(30),
 event_info VARCHAR(200),
 event_region VARCHAR(200),
 timestamp TIMESTAMP);

 --;;

INSERT INTO events(id,event_year,event_month,event_days,event_dates,event_title,event_times,event_location,event_book,event_info,event_region)
VALUES
(1,2019,'jan','tuesday',1,'New Year''s Day Singing','11:15 a.m.-4:00 p.m.','Mapperley Parish Church, Nr Ilkeston, Derbyshire, United Kingdom','denson','Helen Brown','uk'),
(2,2019,'jan','tuesday',1,'Dominic Ciavonne Ziegler Memorial Singing','9-3:30 p.m.','Old Felta Schoolhouse, Healdsburg, California','denson','Lindy Groening','west'

);