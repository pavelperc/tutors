package com.pavelperc.tutors.controller

import com.pavelperc.tutors.domain.Day
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
    fun allSchedules(@RequestParam("day", required = false) day: Day?): List<Schedule> {
        var ans = scheduleRepo.findAll().sortedBy { it.startTime.hour }.sortedBy { it.day.ordinal }

        if (day != null) {
            ans = ans.filter { it.day == day }
        }
        return ans
    }

}