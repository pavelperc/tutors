package com.pavelperc.tutors.repo

import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.domain.Student
import com.pavelperc.tutors.domain.Tutor
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepo : JpaRepository<Person, Long> {
    
    fun findByLogin(login: String) : Person?

}

interface StudentRepo : JpaRepository<Student, Long> {
    
    fun findByLogin(login: String) : Student?
    
}

interface TutorRepo : JpaRepository<Tutor, Long> {
    
    fun findByLogin(login: String) : Tutor?
    
}
