package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Schedule;
import com.pavelperc.tutors.domain.TutorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorScheduleRepo extends JpaRepository<TutorSchedule, Long> {

}
