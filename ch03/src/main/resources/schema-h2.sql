DROP TABLE IF EXISTS COURSES;
CREATE TABLE COURSES (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    category varchar(20) NOT NULL,
    rating int NOT NULL,
    description varchar(1000) NOT NULL
);