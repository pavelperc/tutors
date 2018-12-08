package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Subject;
import com.pavelperc.tutors.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

}
