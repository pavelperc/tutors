package com.pavelperc.tutors.repo

import com.pavelperc.tutors.domain.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepo : JpaRepository<Person, Long> {
    
}
