package com.pavelperc.tutors

import com.pavelperc.tutors.domain.Person
import com.pavelperc.tutors.domain.Student
import com.pavelperc.tutors.domain.Tutor
import com.pavelperc.tutors.domain.TutorCertificate
import com.pavelperc.tutors.repo.PersonRepo
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.security.cert.Certificate


@Component
class DataLoader(
        // autowired by default
        private val personRepo: PersonRepo
) : ApplicationRunner {
    
    
    override fun run(args: ApplicationArguments) {
        
        
        val sergey = Tutor("sergey", "sanya_belij@brigada.com", firstName = "Серёга", lastName = "Безруков")
        
        sergey.certificates.add(TutorCertificate(sergey, "pickup_master", "Покоритель женских сердец."))
        
        personRepo.saveAll(
                listOf(
                        Student("pavel"),
                        Tutor("ivan"),
                        Student("alex"),
                        sergey
                )
        )
    }
}