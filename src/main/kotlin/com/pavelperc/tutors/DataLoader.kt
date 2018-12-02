package com.pavelperc.tutors

import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.repo.PersonRepo
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component


@Component
class DataLoader(
        // autowired by default
        private val personRepo: PersonRepo
) : ApplicationRunner {
    
    
    override fun run(args: ApplicationArguments) {
//        println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA inside DataLoader!!! adding some data!")
        personRepo.saveAll(
                listOf(
                        Person("pavel"),
                        Person("ivan"),
                        Person("alex"),
                        Person("sergey", "sanya_belij@brigada.com", firstName = "Серёга", lastName = "Безруков")
                )
        )
    }
}