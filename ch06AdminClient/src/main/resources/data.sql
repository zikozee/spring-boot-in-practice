INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)  VALUES(1, 'Rapid Spring Boot Application Development', 'Spring', 4, 'Learn Enterprise Application Development with Spring Boot');
INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)  VALUES(2, 'Getting Started with Spring Security DSL', 'Spring', 5, 'Learn Spring Security DSL in Easy Steps');
INSERT INTO COURSES(ID, NAME, CATEGORY, RATING, DESCRIPTION)  VALUES(3, 'Getting Started with Spring Cloud Kubernetes', 'Spring', 3, 'Master Spring Boot Application Deployment with Kubernetes');



INSERT into USERS(username, password, enabled) values ('user','$2a$10$UTvx/YUeLY2o7WndcRpFW.JKtJlHetH9HqVIZ8xvy0w3G5r0cDd0O',true);
INSERT into USERS(username, password, enabled) values ('admin','$2a$10$UTvx/YUeLY2o7WndcRpFW.JKtJlHetH9HqVIZ8xvy0w3G5r0cDd0O', true);

INSERT into AUTHORITIES(username, authority) values ('user','USER');
INSERT into AUTHORITIES(username, authority) values ('admin','ADMIN');


-- password --> password
INSERT INTO CT_USERS(ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, VERIFIED, LOCKED, ACC_CRED_EXPIRED)
VALUES(1, 'John', 'Socket','jsocket', '$2a$10$UTvx/YUeLY2o7WndcRpFW.JKtJlHetH9HqVIZ8xvy0w3G5r0cDd0O', 'jsocket@example.com', TRUE, FALSE, FALSE);

INSERT INTO CT_USERS(ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, VERIFIED, LOCKED, ACC_CRED_EXPIRED)
VALUES(2, 'Steve', 'Smith','smith', '$2a$10$UTvx/YUeLY2o7WndcRpFW.JKtJlHetH9HqVIZ8xvy0w3G5r0cDd0O', 'smith@example.com', FALSE, FALSE, FALSE);