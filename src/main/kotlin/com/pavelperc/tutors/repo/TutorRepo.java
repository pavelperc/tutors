package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Subject;
import com.pavelperc.tutors.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface TutorRepo extends JpaRepository<Tutor, Long> {
    
    @Nullable
    Tutor findByLogin(String login);
    
    List<Tutor> findAllBySubjects(Subject subject);
}
