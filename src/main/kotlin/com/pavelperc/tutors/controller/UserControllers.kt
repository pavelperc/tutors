package com.pavelperc.tutors.controller

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter
import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.repo.PersonRepo
import com.pavelperc.tutors.repo.StudentRepo
import com.pavelperc.tutors.repo.TutorRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.converter.json.MappingJacksonValue
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.PropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter




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
class TutorController(
        private val tutorRepo: TutorRepo
) {
    
    @GetMapping
    fun allTutors(): MutableList<Tutor> {
        return tutorRepo.findAll()
    }
}

@RestController
@RequestMapping("api/students")
class StudentController(
        private val studentRepo: StudentRepo
) {
    
    @GetMapping
    fun allStudents() = studentRepo.findAll()
}