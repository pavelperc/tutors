create sequence hibernate_sequence start with 1 increment by 1
create table course (id bigint not null, name varchar(255) not null, subject_id bigint, primary key (id))
create table person (id bigint not null, email varchar(255), first_name varchar(255), last_name varchar(255), login varchar(255) not null, middle_name varchar(255), primary key (id))
create table student (id bigint not null, primary key (id))
create table subject (id bigint not null, name varchar(255) not null, primary key (id))
create table tutor (id bigint not null, primary key (id))
create table tutor_courses (tutors_id bigint not null, courses_id bigint not null, primary key (tutors_id, courses_id))
create table tutor_subjects (tutor_id bigint not null, subjects_id bigint not null, primary key (tutor_id, subjects_id))
create table tutor_certificate (id bigint not null, description varchar(255) not null, name varchar(255) not null, tutor_id bigint, primary key (id))
alter table course add constraint UK_4xqvdpkafb91tt3hsb67ga3fj unique (name)
alter table person add constraint UK_3tnwg2lomhbqckauuc1997bx7 unique (login)
alter table subject add constraint UK_p1jgir6qcpmqnxt4a8105wsot unique (name)
alter table course add constraint FKm1expnaas0onmafqpktmjixnx foreign key (subject_id) references subject
alter table student add constraint FKslayvtom01idjdexcxh76k935 foreign key (id) references person
alter table tutor add constraint FKs5kvxap3nbwywuplcy7t04keg foreign key (id) references person
alter table tutor_courses add constraint FK8os216pnb4e63o3st1imu1yke foreign key (courses_id) references course
alter table tutor_courses add constraint FKgokokj66y7do6h8y4kpn8t823 foreign key (tutors_id) references tutor
alter table tutor_subjects add constraint FK3wpswxifhr35whtb2nf3eo4p9 foreign key (subjects_id) references subject
alter table tutor_subjects add constraint FKrbwej4ik99xyo11357qqwucco foreign key (tutor_id) references tutor
alter table tutor_certificate add constraint FKbgaq5ffyw2y4c1mjc4bbn3l2h foreign key (tutor_id) references tutor
