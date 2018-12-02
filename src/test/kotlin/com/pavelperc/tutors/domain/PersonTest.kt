package com.pavelperc.tutors.domain

import com.pavelperc.tutors.repo.PersonRepo
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldContainSame
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@DataJpaTest
internal class PersonTest {
    
    @Autowired
    lateinit var personRepo: PersonRepo
    
    @Test
    fun personRepoLoading() {
        personRepo.shouldNotBeNull()
    }
    
    @Test
    fun personTest() {
        personRepo.findAll().shouldBeEmpty()
    
        personRepo.saveAll(listOf(
                Person("pavel"),
                Person("ivan"),
                Person("alex")
        ))
        
        personRepo.findAll()
                .map { it.login }
                .shouldContainSame(listOf("pavel", "ivan", "alex"))
        
    }
    
}