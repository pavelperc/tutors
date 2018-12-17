package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Course
import com.pavelperc.tutors.domain.Day
import com.pavelperc.tutors.domain.TutorSchedule
import com.pavelperc.tutors.exceptions.ConflictException
import com.pavelperc.tutors.exceptions.NotFoundException
import com.pavelperc.tutors.repo.CourseRepo
import com.pavelperc.tutors.repo.TutorRepo
import com.pavelperc.tutors.repo.TutorScheduleRepo
import com.pavelperc.tutors.service.CourseService
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/courses")
class CourseController(
        private val courseRepo: CourseRepo,
        private val tutorRepo: TutorRepo,
        private val tutorScheduleRepo: TutorScheduleRepo,
        private val courseService: CourseService
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

        return courseService.connectTutors(tutorIds, course)
    }


    @PostMapping("{id}/disconnectTutors")
    fun disconnectTutors(@PathVariable("id") course: Course?, @RequestBody tutorIds: List<Long>): Course? {
        // if null then trow an exception else cast to not null
        course ?: throw NotFoundException("Course not found!!!")

        return courseService.disconnectTutors(tutorIds, course)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") course: Course) {
        courseRepo.delete(course)
    }


    @GetMapping("{id}/schedule")
    fun getSchedule(
            @PathVariable("id") course: Course?,
            @RequestParam("day", required = false) day: Day?
    ): MutableList<TutorSchedule>? {
        // if null then trow an exception else cast to not null
        course ?: throw NotFoundException("Course not found!!!")

        var ans = tutorScheduleRepo.findAllByCourse(course)

        if (day != null)
            ans = ans.filter { it.schedule.day == day }
        return ans
    }
}
