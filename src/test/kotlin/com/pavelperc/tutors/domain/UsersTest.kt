package com.pavelperc.tutors.domain

import com.pavelperc.tutors.repo.PersonRepo
import com.pavelperc.tutors.repo.StudentRepo
import com.pavelperc.tutors.repo.TutorRepo
import org.amshove.kluent.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@DataJpaTest
internal class UsersTest {
    
    @Autowired
    lateinit var personRepo: PersonRepo
    @Autowired
    lateinit var studentRepo: StudentRepo
    @Autowired
    lateinit var tutorRepo: TutorRepo

//    val log = LogFactory.getLog("my_logs")!!
    
    @BeforeEach
    fun setUp() {
        personRepo.deleteAll()
        studentRepo.deleteAll()
        tutorRepo.deleteAll()
    }
    
    @Test
    fun reposLoading() {
        personRepo.shouldNotBeNull()
        studentRepo.shouldNotBeNull()
        tutorRepo.shouldNotBeNull()
    }
    
    @Test
    fun splitUsers() {
        personRepo.findAll().shouldBeEmpty()
        
        personRepo.saveAll(listOf(
                Tutor("pavel"),
                Student("ivan"),
                Tutor("alex")
        ))
        
        personRepo.findAll().map { it.login }
                .shouldEqual(listOf("pavel", "ivan", "alex"))
        
        studentRepo.findAll().map { it.login }
                .shouldEqual(listOf("ivan"))
        
        tutorRepo.findAll().map { it.login }
                .shouldEqual(listOf("pavel", "alex"))
    }
    
    
    @Test
    fun `check inheritance in personRepo`() {
        personRepo.findAll().shouldBeEmpty()
        
        personRepo.saveAll(listOf(
                Tutor("pavel"),
                Student("ivan"),
                Tutor("alex")
        ))
        
        val (pavel, ivan, alex) = personRepo.findAll()
        
        pavel shouldBeInstanceOf Tutor::class
        ivan shouldBeInstanceOf Student::class
        alex shouldBeInstanceOf Tutor::class
    }
    
    
    @Test
    fun `add to different repos`() {
        
        tutorRepo.save(Tutor("pavel"))
        tutorRepo.save(Tutor("alex"))
        
        studentRepo.save(Student("ivan"))
        
        
        personRepo.findAll().map { it.login }
                .shouldContainSame(listOf("pavel", "ivan", "alex"))
    }
    
    @Test
    fun testFindByLogin() {
        
        tutorRepo.save(Tutor("pavel"))
        tutorRepo.save(Tutor("alex"))
        
        studentRepo.save(Student("ivan"))
    
    
        personRepo.findByLogin("pavel")
                .shouldNotBeNull()
                .login shouldEqual "pavel"
        
        tutorRepo.findByLogin("pavel")
                .shouldNotBeNull()
                .login shouldEqual "pavel"
        
        studentRepo.findByLogin("pavel").shouldBeNull()
        
    }
    
    
}