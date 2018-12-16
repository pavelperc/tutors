;             
CREATE USER IF NOT EXISTS SA SALT 'd041cacf8f30dab8' HASH '452fe59692b93619836371e7189b2e5873d5be5efdddd9090bcfb6640b22282a' ADMIN;           
CREATE SEQUENCE PUBLIC.HIBERNATE_SEQUENCE START WITH 12;      
CREATE MEMORY TABLE PUBLIC.COURSE(
    ID BIGINT NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    SUBJECT_ID BIGINT
);       
ALTER TABLE PUBLIC.COURSE ADD CONSTRAINT PUBLIC.CONSTRAINT_7 PRIMARY KEY(ID); 
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.COURSE;  
INSERT INTO PUBLIC.COURSE(ID, NAME, SUBJECT_ID) VALUES
(10, 'matan', 1),
(11, 'diskra', 3); 
CREATE MEMORY TABLE PUBLIC.PERSON(
    ID BIGINT NOT NULL,
    EMAIL VARCHAR(255),
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255),
    LOGIN VARCHAR(255) NOT NULL,
    MIDDLE_NAME VARCHAR(255)
);           
ALTER TABLE PUBLIC.PERSON ADD CONSTRAINT PUBLIC.CONSTRAINT_8 PRIMARY KEY(ID); 
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.PERSON;  
INSERT INTO PUBLIC.PERSON(ID, EMAIL, FIRST_NAME, LAST_NAME, LOGIN, MIDDLE_NAME) VALUES
(4, NULL, STRINGDECODE('\u041f\u0430\u0448\u0430'), NULL, 'pavel', NULL),
(5, NULL, STRINGDECODE('\u0414\u0440\u044e\u043d\u044f'), NULL, 'andrey', NULL),
(6, NULL, STRINGDECODE('\u041a\u0430\u0442\u044f'), NULL, 'katja', NULL),
(7, 'super_tutor@hse.ru', STRINGDECODE('\u041f\u0440\u043e\u0441\u0442\u043e \u0412\u0430\u043d\u044f'), NULL, 'ivan', NULL),
(8, 'sanya_belij@brigada.com', STRINGDECODE('\u0421\u0435\u0440\u0451\u0433\u0430'), STRINGDECODE('\u0411\u0435\u0437\u0440\u0443\u043a\u043e\u0432'), 'sergey', NULL);        
CREATE MEMORY TABLE PUBLIC.STUDENT(
    ID BIGINT NOT NULL
);               
ALTER TABLE PUBLIC.STUDENT ADD CONSTRAINT PUBLIC.CONSTRAINT_B PRIMARY KEY(ID);
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.STUDENT; 
INSERT INTO PUBLIC.STUDENT(ID) VALUES
(4),
(5),
(6);       
CREATE MEMORY TABLE PUBLIC.SUBJECT(
    ID BIGINT NOT NULL,
    NAME VARCHAR(255) NOT NULL
);              
ALTER TABLE PUBLIC.SUBJECT ADD CONSTRAINT PUBLIC.CONSTRAINT_BB PRIMARY KEY(ID);               
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.SUBJECT; 
INSERT INTO PUBLIC.SUBJECT(ID, NAME) VALUES
(1, 'math'),
(2, 'russian'),
(3, 'informatics');               
CREATE MEMORY TABLE PUBLIC.TUTOR(
    ID BIGINT NOT NULL
); 
ALTER TABLE PUBLIC.TUTOR ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(ID);  
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.TUTOR;   
INSERT INTO PUBLIC.TUTOR(ID) VALUES
(7),
(8);               
CREATE MEMORY TABLE PUBLIC.TUTOR_COURSES(
    TUTORS_ID BIGINT NOT NULL,
    COURSES_ID BIGINT NOT NULL
); 
ALTER TABLE PUBLIC.TUTOR_COURSES ADD CONSTRAINT PUBLIC.CONSTRAINT_7D PRIMARY KEY(TUTORS_ID, COURSES_ID);      
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.TUTOR_COURSES;           
INSERT INTO PUBLIC.TUTOR_COURSES(TUTORS_ID, COURSES_ID) VALUES
(7, 10),
(8, 11),
(8, 10);  
CREATE MEMORY TABLE PUBLIC.TUTOR_SUBJECTS(
    TUTOR_ID BIGINT NOT NULL,
    SUBJECTS_ID BIGINT NOT NULL
);
ALTER TABLE PUBLIC.TUTOR_SUBJECTS ADD CONSTRAINT PUBLIC.CONSTRAINT_BF PRIMARY KEY(TUTOR_ID, SUBJECTS_ID);     
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.TUTOR_SUBJECTS;          
INSERT INTO PUBLIC.TUTOR_SUBJECTS(TUTOR_ID, SUBJECTS_ID) VALUES
(7, 1),
(7, 3),
(7, 2),
(8, 3);           
CREATE MEMORY TABLE PUBLIC.TUTOR_CERTIFICATE(
    ID BIGINT NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    TUTOR_ID BIGINT
);      
ALTER TABLE PUBLIC.TUTOR_CERTIFICATE ADD CONSTRAINT PUBLIC.CONSTRAINT_C PRIMARY KEY(ID);      
-- 1 +/- SELECT COUNT(*) FROM PUBLIC.TUTOR_CERTIFICATE;       
INSERT INTO PUBLIC.TUTOR_CERTIFICATE(ID, DESCRIPTION, NAME, TUTOR_ID) VALUES
(9, STRINGDECODE('\u041f\u043e\u043a\u043e\u0440\u0438\u0442\u0435\u043b\u044c \u0436\u0435\u043d\u0441\u043a\u0438\u0445 \u0441\u0435\u0440\u0434\u0435\u0446.'), 'lover', 8); 
ALTER TABLE PUBLIC.PERSON ADD CONSTRAINT PUBLIC.UK_3TNWG2LOMHBQCKAUUC1997BX7 UNIQUE(LOGIN);   
ALTER TABLE PUBLIC.COURSE ADD CONSTRAINT PUBLIC.UK_4XQVDPKAFB91TT3HSB67GA3FJ UNIQUE(NAME);    
ALTER TABLE PUBLIC.SUBJECT ADD CONSTRAINT PUBLIC.UK_P1JGIR6QCPMQNXT4A8105WSOT UNIQUE(NAME);   
ALTER TABLE PUBLIC.TUTOR_SUBJECTS ADD CONSTRAINT PUBLIC.FKRBWEJ4IK99XYO11357QQWUCCO FOREIGN KEY(TUTOR_ID) REFERENCES PUBLIC.TUTOR(ID) NOCHECK;
ALTER TABLE PUBLIC.TUTOR ADD CONSTRAINT PUBLIC.FKS5KVXAP3NBWYWUPLCY7T04KEG FOREIGN KEY(ID) REFERENCES PUBLIC.PERSON(ID) NOCHECK;              
ALTER TABLE PUBLIC.TUTOR_COURSES ADD CONSTRAINT PUBLIC.FK8OS216PNB4E63O3ST1IMU1YKE FOREIGN KEY(COURSES_ID) REFERENCES PUBLIC.COURSE(ID) NOCHECK;              
ALTER TABLE PUBLIC.STUDENT ADD CONSTRAINT PUBLIC.FKSLAYVTOM01IDJDEXCXH76K935 FOREIGN KEY(ID) REFERENCES PUBLIC.PERSON(ID) NOCHECK;            
ALTER TABLE PUBLIC.COURSE ADD CONSTRAINT PUBLIC.FKM1EXPNAAS0ONMAFQPKTMJIXNX FOREIGN KEY(SUBJECT_ID) REFERENCES PUBLIC.SUBJECT(ID) NOCHECK;    
ALTER TABLE PUBLIC.TUTOR_CERTIFICATE ADD CONSTRAINT PUBLIC.FKBGAQ5FFYW2Y4C1MJC4BBN3L2H FOREIGN KEY(TUTOR_ID) REFERENCES PUBLIC.TUTOR(ID) NOCHECK;             
ALTER TABLE PUBLIC.TUTOR_SUBJECTS ADD CONSTRAINT PUBLIC.FK3WPSWXIFHR35WHTB2NF3EO4P9 FOREIGN KEY(SUBJECTS_ID) REFERENCES PUBLIC.SUBJECT(ID) NOCHECK;           
ALTER TABLE PUBLIC.TUTOR_COURSES ADD CONSTRAINT PUBLIC.FKGOKOKJ66Y7DO6H8Y4KPN8T823 FOREIGN KEY(TUTORS_ID) REFERENCES PUBLIC.TUTOR(ID) NOCHECK;
