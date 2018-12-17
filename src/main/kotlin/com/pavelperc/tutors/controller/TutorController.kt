package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Course
import com.pavelperc.tutors.domain.Day
import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.domain.TutorSchedule
import com.pavelperc.tutors.repo.TutorRepo
import com.pavelperc.tutors.repo.TutorScheduleRepo
import javassist.NotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/tutors")
class TutorController(
        private val tutorRepo: TutorRepo,
        private val tutorScheduleRepo: TutorScheduleRepo
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


    @GetMapping("{id}/slots")
    fun getTutorSlots(
            @PathVariable("id") tutor: Tutor?,
            @RequestParam("day", required = false) day: Day?
    ): List<TutorSchedule> {
        tutor ?: throw NotFoundException("Tutor not found!!!")

        if (day == null)
            return tutor.tutorSchedules.filter { it.isEmpty }
        else
            return tutor.tutorSchedules.filter { it.isEmpty && it.schedule.day == day }
//        return tutorScheduleRepo.findAllByStudentIsNullAndSchedule_DayEquals()
    }

    @GetMapping("{id}/busy")
    fun getTutorBusy(
            @PathVariable("id") tutor: Tutor?,
            @RequestParam("courseName", required = false) courseName: String?,
            @RequestParam("day", required = false) day: Day?
    ): List<TutorSchedule> {
        tutor ?: throw NotFoundException("Tutor not found!!!")

        var ans = tutor.tutorSchedules.filter { !it.isEmpty }

        if (day != null) {
            ans = ans.filter { it.schedule.day == day }
        }
        if (courseName != null) {
            ans = ans.filter { it.course?.name == courseName }
        }

        return ans
    }

@GetMapping("{id}/schedule")
    fun getTutorSchedule(
            @PathVariable("id") tutor: Tutor?,
            @RequestParam("day", required = false) day: Day?
    ): List<TutorSchedule> {
        tutor ?: throw NotFoundException("Tutor not found!!!")

        var ans = tutor.tutorSchedules.toList()

        if (day != null) {
            ans = ans.filter { it.schedule.day == day }
        }
        return ans
    }

}