package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Subject
import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.repo.SubjectRepo
import com.pavelperc.tutors.repo.TutorRepo
import javassist.NotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/subjects")
class SubjectController(
        private val subjectRepo: SubjectRepo,
        private val tutorRepo: TutorRepo
) {
    
    
    @GetMapping
    fun allSubjects(@RequestParam(value = "name", required = false) name: String?): Any {
        if (name == null) {
            return subjectRepo.findAll()
        } else {
            return subjectRepo.findByName(name) ?: throw NotFoundException("Subject with name=$name not found!!!")
        }
    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") subject: Subject?) = subject ?: throw NotFoundException("Subject not found!!!")
    
    @GetMapping("{id}/tutors")
    fun allTutors(@PathVariable("id") subject: Subject?): List<Tutor> {
        subject ?: throw NotFoundException("Subject not found!!!")
        return tutorRepo.findAllBySubjects(subject)
    }
    
    
    @PostMapping
    fun create(@RequestBody subject: Subject): Subject {
        return subjectRepo.save(subject)
    }
    
    @PutMapping("{id}")
    fun update(@PathVariable("id") subjectFromDB: Subject?, @RequestBody subject: Subject): Subject {
        subjectFromDB ?: throw NotFoundException("Subject not found!!!")
        
        BeanUtils.copyProperties(subject, subjectFromDB, "id")
        return subjectRepo.save(subject)
    }
    
    
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") subject: Subject?) {
        subject ?: throw NotFoundException("Subject not found!!!")
        
        subjectRepo.delete(subject)
    }
}
