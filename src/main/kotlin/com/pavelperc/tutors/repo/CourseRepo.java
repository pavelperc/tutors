package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {

}
