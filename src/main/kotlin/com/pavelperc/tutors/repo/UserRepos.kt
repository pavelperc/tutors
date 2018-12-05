package com.pavelperc.tutors.repo

import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.domain.Student
import com.pavelperc.tutors.domain.Tutor
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepo : JpaRepository<Person, Long> {
    
}

interface StudentRepo : JpaRepository<Student, Long> {
    
}

interface TutorRepo : JpaRepository<Tutor, Long> {
    
}
