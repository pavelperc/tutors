package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Course;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
    
    @Nullable
    Course findByName(@NotNull String name);
}
