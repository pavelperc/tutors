package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Day
import com.pavelperc.tutors.domain.Student
import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.domain.TutorSchedule
import com.pavelperc.tutors.repo.StudentRepo
import com.pavelperc.tutors.repo.TutorScheduleRepo
import javassist.NotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/students")
class StudentController(
        private val studentRepo: StudentRepo,
        private val tutorScheduleRepo: TutorScheduleRepo
) {

    @GetMapping
    fun allStudents(@RequestParam(value = "login", required = false) login: String?): Any {
        if (login == null) {
            return studentRepo.findAll()
        } else {
            return studentRepo.findByLogin(login) ?: throw NotFoundException("Student with login=$login not found!!!")
        }
    }

    @GetMapping("{id}")
    fun getById(@PathVariable("id") student: Student?) = student ?: throw NotFoundException("Student not found!!!")

    @PostMapping
    fun create(@RequestBody student: Student): Student {
        return studentRepo.save(student)
    }

    @PutMapping("{id}")
    fun update(@PathVariable("id") studentFromDB: Student?, @RequestBody student: Student): Student {
        studentFromDB ?: throw NotFoundException("Student not found!!!")

        BeanUtils.copyProperties(student, studentFromDB, "id")
        return studentRepo.save(student)
    }


    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") student: Student?) {
        student ?: throw NotFoundException("Student not found!!!")
        studentRepo.delete(student)
    }


    @GetMapping("{id}/schedule")
    fun getSchedule(
            @PathVariable("id") student: Student?,
            @RequestParam("day", required = false) day: Day?
    ): List<TutorSchedule> {
        student ?: throw NotFoundException("Tutor not found!!!")

        return tutorScheduleRepo.findAllByStudent(student)
                .let { ans ->
                    if (day != null) ans.filter { it.schedule.day == day }
                    else ans
                }
    }

}