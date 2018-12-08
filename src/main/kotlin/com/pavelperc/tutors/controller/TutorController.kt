package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.repo.TutorRepo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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