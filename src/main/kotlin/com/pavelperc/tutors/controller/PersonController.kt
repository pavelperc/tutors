package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.repo.PersonRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/persons")
class PersonController(
        private val personRepo: PersonRepo
) {
    
    @GetMapping
    fun allPersons(): List<Person> {
        return personRepo.findAll()
    }
}