package com.pavelperc.tutors

import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.domain.Student
import com.pavelperc.tutors.domain.Tutor
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
        personRepo.saveAll(
                listOf(
                        Student("pavel"),
                        Tutor("ivan"),
                        Student("alex"),
                        Tutor("sergey", "sanya_belij@brigada.com", firstName = "Серёга", lastName = "Безруков")
                )
        )
    }
}