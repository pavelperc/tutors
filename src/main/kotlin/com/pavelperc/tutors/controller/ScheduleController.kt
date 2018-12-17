package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Schedule
import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.domain.TutorSchedule
import com.pavelperc.tutors.repo.ScheduleRepo
import com.pavelperc.tutors.repo.TutorRepo
import javassist.NotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/schedules")
class ScheduleController(
        private val tutorRepo: TutorRepo,
        private val scheduleRepo: ScheduleRepo
) {
    
    @GetMapping
    fun allSchedules(): List<Schedule> {
        return scheduleRepo.findAll(Sort.by("day", "startTime"))
    }

}