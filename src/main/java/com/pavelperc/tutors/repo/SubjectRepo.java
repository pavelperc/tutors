package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Subject;
import com.pavelperc.tutors.domain.Tutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    
    @Nullable
    Subject findByName(@NotNull String name);
}
