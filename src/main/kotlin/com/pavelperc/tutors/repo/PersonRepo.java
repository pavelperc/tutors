package com.pavelperc.tutors.repo;

import com.pavelperc.tutors.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

public interface PersonRepo extends JpaRepository<Person, Long> {
    
    @Nullable
    Person findByLogin(String login);
}