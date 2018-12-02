package com.pavelperc.tutors.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/persons")
class PersonController {
    
    @GetMapping
    fun allPersons(): List<Map<String, String>> {
        return listOf(
                mapOf(Pair("id", "1"), Pair("login", "pavel")),
                mapOf(Pair("id", "2"), Pair("login", "ivan")),
                mapOf(Pair("id", "3"), Pair("login", "alex"))
        )
    }
}