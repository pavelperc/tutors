package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Course
import com.pavelperc.tutors.exceptions.ConflictException
import com.pavelperc.tutors.exceptions.NotFoundException
import com.pavelperc.tutors.repo.CourseRepo
import com.pavelperc.tutors.repo.TutorRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/courses")
class CourseController(
        private val courseRepo: CourseRepo,
        private val tutorRepo: TutorRepo
) {
    
    val logger = LoggerFactory.getLogger(CourseController::class.java)
    
    @GetMapping
    fun allCourses(@RequestParam(value = "name", required = false) name: String?): Any {
        if (name == null) {
            return courseRepo.findAll()
        } else {
            return courseRepo.findByName(name) ?: throw NotFoundException("Course with name=$name not found!!!")
        }
    }
    
    @GetMapping("{id}")
    fun getById(@PathVariable("id") course: Course?) = course ?: throw NotFoundException("Course not found!!!")
    
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
    
    
    @PostMapping("{id}/connectTutors")
    fun connectTutors(@PathVariable("id") course: Course?, @RequestBody tutorIds: List<Long>): Course? {
        // if null then trow an exception else cast to not null
        course ?: throw NotFoundException("Course not found!!!")
        
        val tutors = tutorRepo.findAllById(tutorIds)
        logger.debug("In connectTutors. Received tutors: $tutors")
        
        tutors.forEach { tutor ->
            if (!tutor.subjects.contains(course.subject))
                throw ConflictException("Can not connect course ${course.name} with tutor $tutor. Tutor doesn't have course subject ${course.subject.name}.")
        }
        
        course.connectTutors(tutors)
        
        courseRepo.save(course)
        tutorRepo.saveAll(tutors)
        
        return course
    }
    
    @PostMapping("{id}/disconnectTutors")
    fun disconnectTutors(@PathVariable("id") course: Course?, @RequestBody tutorIds: List<Long>): Course? {
        // if null then trow an exception else cast to not null
        course ?: throw NotFoundException("Course not found!!!")
        
        val tutors = tutorRepo.findAllById(tutorIds)
        logger.debug("In disconnectTutors. Received tutors: $tutors")
        
        course.disconnectTutors(tutors)
        
        courseRepo.save(course)
        tutorRepo.saveAll(tutors)
        
        return course
    }
    
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") course: Course) {
        courseRepo.delete(course)
    }
}
