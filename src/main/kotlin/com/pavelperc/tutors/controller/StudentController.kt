package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Student
import com.pavelperc.tutors.repo.StudentRepo
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/students")
class StudentController(
        private val studentRepo: StudentRepo
) {
    
    @GetMapping
    fun allStudents(@RequestParam(value = "login", required = false)  login: String?): Any? {
        if (login == null) {
            return studentRepo.findAll()
        }
        else {
            val found = studentRepo.findByLogin(login)
            return found
        }
    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") student: Student?) = student

    @PostMapping
    fun create(@RequestBody student: Student): Student {
        return studentRepo.save(student)
    }
    
    @PutMapping("{id}")
    fun update(@PathVariable("id") studentFromDB: Student, @RequestBody student: Student): Student {
        BeanUtils.copyProperties(student, studentFromDB, "id")
        return studentRepo.save(student)
    }
    
    
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") student: Student) {
        studentRepo.delete(student)
    }
    
}