package com.pavelperc.tutors.service

import com.pavelperc.tutors.controller.CourseController
import com.pavelperc.tutors.domain.Course
import com.pavelperc.tutors.exceptions.ConflictException
import com.pavelperc.tutors.repo.CourseRepo
import com.pavelperc.tutors.repo.TutorRepo
import com.pavelperc.tutors.repo.TutorScheduleRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TutorScheduleService(
        val tutorRepo: TutorRepo,
        val tutorScheduleRepo: TutorScheduleRepo
) {




}