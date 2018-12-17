package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorScheduleRepo extends JpaRepository<TutorSchedule, Long> {

    List<TutorSchedule> findAllByStudent(@NotNull Student student);

    List<TutorSchedule> findAllByStudentIsNullAndSchedule_DayEquals(Day day);

    List<TutorSchedule> findAllByCourse(@NotNull Course course);


}
