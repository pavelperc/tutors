package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.repo.PersonRepo
import com.pavelperc.tutors.repo.StudentRepo
import com.pavelperc.tutors.repo.TutorRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/persons")
class PersonController(
        private val personRepo: PersonRepo
) {
    
    @GetMapping
    fun allPersons() = personRepo.findAll()
}

@RestController
@RequestMapping("api/tutors")
class TutorsController(
        private val tutorRepo: TutorRepo
) {
    
    @GetMapping
    fun allTutors() = tutorRepo.findAll()
}

@RestController
@RequestMapping("api/students")
class StudentsController(
        private val studentRepo: StudentRepo
) {
    
    @GetMapping
    fun allStudents() = studentRepo.findAll()
}