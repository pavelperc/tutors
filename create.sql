create table PERSON
(
  ID          BIGINT       not null
    primary key,
  EMAIL       VARCHAR(255),
  FIRST_NAME  VARCHAR(255),
  LAST_NAME   VARCHAR(255),
  LOGIN       VARCHAR(255) not null
    unique,
  MIDDLE_NAME VARCHAR(255)
);

create table SCHEDULE
(
  ID         BIGINT not null
    primary key,
  DAY        VARCHAR(255),
  END_TIME   TIME(8),
  START_TIME TIME(8),
  unique (DAY, START_TIME, END_TIME)
);

create table STUDENT
(
  ID BIGINT not null
    primary key,
  constraint FKSLAYVTOM01IDJDEXCXH76K935
    foreign key (ID) references PERSON
);

create table SUBJECT
(
  ID   BIGINT       not null
    primary key,
  NAME VARCHAR(255) not null
    unique
);

create table COURSE
(
  ID         BIGINT       not null
    primary key,
  NAME       VARCHAR(255) not null
    unique,
  SUBJECT_ID BIGINT,
  constraint FKM1EXPNAAS0ONMAFQPKTMJIXNX
    foreign key (SUBJECT_ID) references SUBJECT
);

create table TUTOR
(
  ID BIGINT not null
    primary key,
  constraint FKS5KVXAP3NBWYWUPLCY7T04KEG
    foreign key (ID) references PERSON
);

create table TUTOR_CERTIFICATE
(
  ID          BIGINT       not null
    primary key,
  DESCRIPTION VARCHAR(255) not null,
  NAME        VARCHAR(255) not null,
  TUTOR_ID    BIGINT,
  constraint FKBGAQ5FFYW2Y4C1MJC4BBN3L2H
    foreign key (TUTOR_ID) references TUTOR
);

create table TUTOR_COURSES
(
  TUTORS_ID  BIGINT not null,
  COURSES_ID BIGINT not null,
  primary key (TUTORS_ID, COURSES_ID),
  constraint FK8OS216PNB4E63O3ST1IMU1YKE
    foreign key (COURSES_ID) references COURSE,
  constraint FKGOKOKJ66Y7DO6H8Y4KPN8T823
    foreign key (TUTORS_ID) references TUTOR
);

create table TUTOR_SCHEDULE
(
  ID          BIGINT not null
    primary key,
  COURSE_ID   BIGINT,
  SCHEDULE_ID BIGINT not null,
  STUDENT_ID  BIGINT,
  TUTOR_ID    BIGINT not null,
  unique (TUTOR_ID, SCHEDULE_ID),
  constraint FK2SICNGDIX45FQNBEM8QR9BIRB
    foreign key (TUTOR_ID) references TUTOR,
  constraint FK4SFKFYVHJ8ANQD3J2QP26QCNY
    foreign key (STUDENT_ID) references STUDENT,
  constraint FKO6JFF0F7NDUGJ28HOUGGAKFKY
    foreign key (SCHEDULE_ID) references SCHEDULE,
  constraint FKOXCQBV49KFT4UCSX9TJDIX2QJ
    foreign key (COURSE_ID) references COURSE
);

create table TUTOR_SUBJECTS
(
  TUTOR_ID    BIGINT not null,
  SUBJECTS_ID BIGINT not null,
  primary key (TUTOR_ID, SUBJECTS_ID),
  constraint FK3WPSWXIFHR35WHTB2NF3EO4P9
    foreign key (SUBJECTS_ID) references SUBJECT,
  constraint FKRBWEJ4IK99XYO11357QQWUCCO
    foreign key (TUTOR_ID) references TUTOR
);

