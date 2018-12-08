package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Course
import com.pavelperc.tutors.repo.CourseRepo
import javassist.NotFoundException
import org.omg.CosNaming.NamingContextPackage.NotFound
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/courses")
class CourseController(
        private val courseRepo: CourseRepo
) {
    
    
    @GetMapping
    fun allCourses(@RequestParam(value = "name", required = false)  name: String?): Any {
        if (name == null) {
            return courseRepo.findAll()
        }
        else {
            return courseRepo.findByName(name) ?: throw NotFoundException("Course with name=$name not found!!!")
        }
    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") course: Course?)
            = course ?: throw NotFoundException("Course not found!!!")
    
    @PostMapping
    fun create(@RequestBody course: Course): Course {
        return courseRepo.save(course)
    }
    
    @PutMapping("{id}")
    fun update(@PathVariable("id") courseFromDB: Course?, @RequestBody course: Course): Course {
        // if null then trow an exception else cast to not null
        courseFromDB ?: throw NotFoundException("Course not found!!!")
        
        BeanUtils.copyProperties(course, courseFromDB, "id")
        return courseRepo.save(course)
    }
    
    
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") course: Course) {
        courseRepo.delete(course)
    }
}
