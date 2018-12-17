package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Day;
import com.pavelperc.tutors.domain.Schedule;
import com.pavelperc.tutors.domain.Student;
import com.pavelperc.tutors.domain.TutorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorScheduleRepo extends JpaRepository<TutorSchedule, Long> {

    List<TutorSchedule> findAllByStudentEquals(Student student);

    List<TutorSchedule> findAllByStudentIsNullAndSchedule_DayEquals(Day day);


}
