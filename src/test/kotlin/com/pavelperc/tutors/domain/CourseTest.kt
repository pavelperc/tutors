package com.pavelperc.tutors.domain

import com.pavelperc.tutors.repo.CourseRepo
import com.pavelperc.tutors.repo.SubjectRepo
import com.pavelperc.tutors.repo.TutorRepo
import org.amshove.kluent.shouldContainSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
internal class CourseTest {
    @Autowired
    lateinit var subjectRepo: SubjectRepo
    @Autowired
    lateinit var tutorRepo: TutorRepo
    @Autowired
    lateinit var courseRepo: CourseRepo
    
    
    @Test
    fun connectWithTutors() {
        // retrieve back with updated ids
    
        val (tutor1, tutor2, tutor3) = tutorRepo.saveAll(listOf(
                Tutor("tutor1"),
                Tutor("tutor2"),
                Tutor("tutor3")
        ))
        
        val (math, russian, informatics) = subjectRepo.saveAll(listOf(
                Subject("math"),
                Subject("russian"),
                Subject("informatics")
        ))
        
        
        tutor1.subjects = mutableSetOf(math, russian)
        tutor2.subjects = mutableSetOf(math, informatics)
        tutor3.subjects = mutableSetOf(math, russian, informatics)
        
        tutorRepo.saveAll(listOf(tutor1, tutor2, tutor3))
        
        
        var matan = Course("matan", math, mutableSetOf(tutor1, tutor2))
        
        // don't check invalid tutors here (without needed subject name)
        var diskra = Course("diskra", informatics, mutableSetOf(tutor2, tutor3))
        
        
        
        matan = courseRepo.save(matan)
        diskra = courseRepo.save(diskra)
        
        
        val tutor2Courses = tutorRepo.findByLogin("tutor2")!!.courses
        
        
        println("Found for tutor2: $tutor2Courses")
        
        tutor2Courses shouldContainSame listOf(matan, diskra)
        
    }
}