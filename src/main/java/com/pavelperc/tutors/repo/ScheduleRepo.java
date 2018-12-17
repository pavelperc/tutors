package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Course;
import com.pavelperc.tutors.domain.Schedule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

}
