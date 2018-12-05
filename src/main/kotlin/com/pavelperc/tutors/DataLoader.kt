package com.pavelperc.tutors

import com.pavelperc.tutors.domain.*
import com.pavelperc.tutors.repo.PersonRepo
import com.pavelperc.tutors.repo.SubjectRepo
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.security.cert.Certificate


@Component
class DataLoader(
        // autowired by default
        private val personRepo: PersonRepo,
        private val subjectRepo: SubjectRepo
) : ApplicationRunner {
    
    
    override fun run(args: ApplicationArguments) {
        
        // SETUP H2 DATABASE TO START A SERVER!!!!!!!! (for debug with intellij idea)
        org.h2.tools.Server.createTcpServer().start();
        
        
        // Subjects
        val (math, informatics, russian) = listOf(Subject("math"), Subject("informatics"), Subject("russian"))
        
        // our best tutors
        val sergey = Tutor("sergey", "sanya_belij@brigada.com", firstName = "Серёга", lastName = "Безруков")
        val ivan = Tutor("ivan", "super_tutor@hse.ru", firstName = "Просто Ваня")
        
        
        sergey.certificates.add(TutorCertificate(sergey, "lover", "Покоритель женских сердец."))
        
        ivan.subjects = mutableSetOf(math, informatics, russian)
        sergey.subjects.add(informatics)
        
        // our best students
        val pavel = Student("pavel", firstName = "Паша")
        val andrey = Student("andrey", firstName = "Дрюня")
        val katja = Student("katja", firstName = "Катя")
        
        
        subjectRepo.saveAll(listOf(math, russian, informatics))
        personRepo.saveAll(listOf(sergey, ivan, pavel, andrey, katja))
    }
}