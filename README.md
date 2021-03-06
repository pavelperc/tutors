# tutors
Server and client for project StudentsTeach. Landing page: http://steach.tilda.ws/

Google document for this project:
https://docs.google.com/document/d/1ZWevz8CirO7A6i_wvqahBnzg3QKwzCLrW4XHW5tHq04/edit?usp=sharing

Api can be found here:
https://github.com/pavelperc/tutors/blob/master/src/main/resources/static/ApiHelp.html


#### Краткое описание программы:

Данная программа предназначена для составления как курсов, так и индивидуальных занятий для репетиторов и учеников. Администратор может вносить, изменять, удалять и получать необходимые результаты, касающихся репетиторов, учеников, предметов, курсов, индивидуальных занятий и курсов.

#### Функциональные требования:

1) Возможность редактировать все таблицы (учеников, преподавателей, курсов, предметов)

2) Возможность вывода всей информации о курсе

3) Возможность получения свободного времени всех (или заданного) преподавателей по интересующему (-им) предмету (-ам)

4) Возможность получения расписания преподавателей с каждым учеником

5) Возможность привязывания конкретного ученика к преподавателю, составление уникального расписания

6) Возможность объединять несколько предметов в один курс

7) Возможность обновления расписания

#### Нефункциональные требования:

1) Для более корректной работы программы, в таблице расписание преподавателя в столбце StudentID допустимо значение NULL, так как это упрощает работу с возможностью добавления нового ученика и выдачу некоторых запросов (свободное время преподавателя, в какое время он работает)

2) Во многих таблицах наблюдается UNIQUE KEY, что способствует избежать дублирования информации
