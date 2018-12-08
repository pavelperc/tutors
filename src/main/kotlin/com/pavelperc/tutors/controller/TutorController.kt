package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.repo.TutorRepo
import javassist.NotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/tutors")
class TutorController(
        private val tutorRepo: TutorRepo
) {
    
    @GetMapping
    fun allTutors(@RequestParam(value = "login", required = false) login: String?): Any {
        if (login == null) {
            return tutorRepo.findAll()
        } else {
            return tutorRepo.findByLogin(login) ?: throw NotFoundException("Tutor with login=$login not found!!!")
        }
    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") tutor: Tutor?) = tutor ?: throw NotFoundException("Tutor not found!!!")
    
    @PostMapping
    fun create(@RequestBody tutor: Tutor): Tutor {
        return tutorRepo.save(tutor)
    }
    
    @PutMapping("{id}")
    fun update(@PathVariable("id") tutorFromDB: Tutor?, @RequestBody tutor: Tutor): Tutor {
        tutorFromDB ?: throw NotFoundException("Tutor not found!!!")
        
        BeanUtils.copyProperties(tutor, tutorFromDB, "id")
        return tutorRepo.save(tutor)
    }
    
    
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") tutor: Tutor?) {
        tutor ?: throw NotFoundException("Tutor not found!!!")
        tutorRepo.delete(tutor)
    }
}