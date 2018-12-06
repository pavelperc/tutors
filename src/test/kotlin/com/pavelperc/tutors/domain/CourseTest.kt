package com.pavelperc.tutors.domain

import com.pavelperc.tutors.repo.CourseRepo
import com.pavelperc.tutors.repo.SubjectRepo
import com.pavelperc.tutors.repo.TutorRepo
import com.pavelperc.tutors.utils.getOrNull
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldContainSame
import org.amshove.kluent.shouldNotContain
import org.junit.jupiter.api.BeforeEach
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
    
    
    @BeforeEach
    fun clearRepos() {
        subjectRepo.deleteAll()
        tutorRepo.deleteAll()
        courseRepo.deleteAll()
    }
    
    @Test
    fun `connect with tutors`() {
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
        
    
        // don't check invalid tutors here (without needed subject name)
        var matan = Course("matan", math, owner = tutor1)
        matan.connectTutors(listOf(tutor1, tutor2))
        
        var diskra = Course("diskra", informatics, owner = tutor2)
        diskra.connectTutors(listOf(tutor2, tutor3))
        
        tutor2.courses shouldContainSame listOf(matan, diskra)
        
        
        // save courses and tutors
        matan = courseRepo.save(matan)
        diskra = courseRepo.save(diskra)
        tutorRepo.saveAll(listOf(tutor1, tutor2, tutor3))
        
        tutorRepo.findByLogin("tutor2")!!.courses
                .shouldContainSame(listOf(matan, diskra))
    }
    
    
    @Test
    fun `connect with tutor with different subjects`() {
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
        
        
        val matan = courseRepo.save(Course("matan", math, tutor1))
        matan.connectTutors(listOf(tutor1))
        
        val diskra = courseRepo.save(Course("diskra", informatics, owner = tutor2))
        
        tutor1.subjects shouldNotContain informatics
        // should add informatics to tutors courses
        diskra.connectTutors(listOf(tutor1, tutor2, tutor3))
        tutor1.subjects shouldContain informatics
    
        
        courseRepo.saveAll(listOf(matan, diskra))
        tutorRepo.saveAll(listOf(tutor1, tutor2, tutor3))
        
        
        val tutor1Courses = tutorRepo.findByLogin("tutor1")!!.courses
        
        tutor1Courses shouldContainSame listOf(diskra, matan)
    }
    
    @Test
    fun `connect and disconnect tutor`() {
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
        tutor2.subjects = mutableSetOf(informatics)
        tutor3.subjects = mutableSetOf(math, russian, informatics)
        
        tutorRepo.saveAll(listOf(tutor1, tutor2, tutor3))
        
        
        val matan = courseRepo.save(Course("matan", math, owner = tutor1))
        // don't check invalid tutors here (without needed subject name)
        val diskra = courseRepo.save(Course("diskra", informatics, owner = tutor1))
    
    
        matan.connectTutors(tutor1, tutor3)
        diskra.connectTutors(tutor2, tutor3)
        
        courseRepo.saveAll(listOf(matan, diskra))
        tutorRepo.saveAll(listOf(tutor1, tutor2, tutor3))
        
        tutorRepo.findByLogin("tutor1")!!.courses shouldContainSame listOf(matan)
        tutorRepo.findByLogin("tutor2")!!.courses shouldContainSame listOf(diskra)
        tutorRepo.findByLogin("tutor3")!!.courses shouldContainSame listOf(matan, diskra)
        courseRepo.findById(matan.id).get().tutors.map { it.login } shouldContainSame listOf("tutor1", "tutor3")
        courseRepo.findById(diskra.id).get().tutors.map { it.login } shouldContainSame listOf("tutor2", "tutor3")
        
        diskra.disconnectTutors(listOf(tutor1, tutor2, tutor3))
        matan.disconnectTutors(listOf(tutor1, tutor2, tutor3))
        
        diskra.tutors.shouldBeEmpty()
        matan.tutors.shouldBeEmpty()
        listOf(tutor1, tutor2, tutor3).forEach { it.courses.shouldBeEmpty() }
        
        
        courseRepo.saveAll(listOf(matan, diskra))
        tutorRepo.saveAll(listOf(tutor1, tutor2, tutor3))
        
        tutorRepo.findAll().forEach { it.courses.shouldBeEmpty() }
        courseRepo.findAll().forEach { it.tutors.shouldBeEmpty() }
    }
}


