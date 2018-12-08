package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

public interface StudentRepo extends JpaRepository<Student, Long> {
    
    @Nullable
    Student findByLogin(String login);
    
}