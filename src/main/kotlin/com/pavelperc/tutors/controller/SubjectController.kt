package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Subject
import com.pavelperc.tutors.repo.SubjectRepo
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/subjects")
class SubjectController(
        private val subjectRepo: SubjectRepo
) {
    
    
    @GetMapping
    fun allSubjects(@RequestParam(value = "name", required = false)  name: String?): Any? {
        if (name == null) {
            return subjectRepo.findAll()
        }
        else {
            return subjectRepo.findByName(name)
        }
    }

//    @GetMapping("{login}")
//    fun getByLogin(@PathVariable login: String): Subject? {
//        return subjectRepo.findByLogin(login)
//    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") subject: Subject?) = subject
    
    @PostMapping
    fun create(@RequestBody subject: Subject): Subject {
        return subjectRepo.save(subject)
    }
    
    @PutMapping("{id}")
    fun update(@PathVariable("id") subjectFromDB: Subject, @RequestBody subject: Subject): Subject {
        BeanUtils.copyProperties(subject, subjectFromDB, "id")
        return subjectRepo.save(subject)
    }
    
    
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") subject: Subject) {
        subjectRepo.delete(subject)
    }
}
