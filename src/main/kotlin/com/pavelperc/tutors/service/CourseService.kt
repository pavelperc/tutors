package com.pavelperc.tutors.service

import com.pavelperc.tutors.controller.CourseController
import com.pavelperc.tutors.domain.Course
import com.pavelperc.tutors.exceptions.ConflictException
import com.pavelperc.tutors.repo.CourseRepo
import com.pavelperc.tutors.repo.TutorRepo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CourseService(
        val tutorRepo: TutorRepo,
        val courseRepo: CourseRepo
) {
    val logger = LoggerFactory.getLogger(CourseController::class.java)


    @Transactional
    @Throws(ConflictException::class)
    fun connectTutors(tutorIds: List<Long>, course: Course): Course {
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

    @Transactional
    fun disconnectTutors(tutorIds: List<Long>, course: Course): Course {
        val tutors = tutorRepo.findAllById(tutorIds)
        logger.debug("In disconnectTutors. Received tutors: $tutors")

        course.disconnectTutors(tutors)

        courseRepo.save(course)
        tutorRepo.saveAll(tutors)

        return course
    }

}