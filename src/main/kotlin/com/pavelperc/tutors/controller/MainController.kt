package com.pavelperc.tutors.controller

import com.pavelperc.tutors.repo.PersonRepo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Controller("api")
class MainController(
        private val personRepo: PersonRepo
) {

//    @RequestMapping
//    fun getApiHelp() = "ApiHelp"
}