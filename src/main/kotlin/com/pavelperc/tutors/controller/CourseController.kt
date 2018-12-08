package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Course
import com.pavelperc.tutors.repo.CourseRepo
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/courses")
class CourseController(
        private val courseRepo: CourseRepo
) {
    
    
    @GetMapping
    fun allCourses(@RequestParam(value = "name", required = false)  name: String?): Any? {
        if (name == null) {
            return courseRepo.findAll()
        }
        else {
            return courseRepo.findByName(name)
        }
    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") course: Course?) = course
    
    @PostMapping
    fun create(@RequestBody course: Course): Course {
        return courseRepo.save(course)
    }
    
    @PutMapping("{id}")
    fun update(@PathVariable("id") courseFromDB: Course, @RequestBody course: Course): Course {
        BeanUtils.copyProperties(course, courseFromDB, "id")
        return courseRepo.save(course)
    }
    
    
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") course: Course) {
        courseRepo.delete(course)
    }
}
