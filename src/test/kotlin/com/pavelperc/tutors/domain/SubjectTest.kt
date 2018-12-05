package com.pavelperc.tutors.domain

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
internal class SubjectTest {
    @Autowired
    lateinit var subjectRepo: SubjectRepo
    @Autowired
    lateinit var tutorRepo: TutorRepo
    
    
    @Test
    fun connectWithTutors() {
        tutorRepo.saveAll(listOf(
                Tutor("tutor1"),
                Tutor("tutor2"),
                Tutor("tutor3")
        ))
        
        subjectRepo.saveAll(listOf(
                Subject("math"),
                Subject("russian"),
                Subject("informatics")
        ))
        
        // retrieve back with updated ids
        val (tutor1, tutor2, tutor3) = tutorRepo.findAll()
        
        val (math, russian, informatics) = subjectRepo.findAll()
        
        tutor1.subjects = mutableSetOf(math, russian)
        tutor2.subjects = mutableSetOf(math)
        tutor3.subjects = mutableSetOf(math, russian, informatics)
        
        
        tutorRepo.saveAll(listOf(tutor1, tutor2, tutor3))
        
        tutorRepo.findAllBySubjects(math)
                .map { it.login }
                .shouldContainSame(listOf("tutor1", "tutor2", "tutor3"))
        
        
        tutorRepo.findAllBySubjects(russian)
                .map { it.login }
                .shouldContainSame(listOf("tutor1", "tutor3"))
        
        
        tutorRepo.findAllBySubjects(informatics)
                .map { it.login }
                .shouldContainSame(listOf("tutor3"))
    }
}