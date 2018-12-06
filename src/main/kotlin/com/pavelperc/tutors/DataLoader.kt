package com.pavelperc.tutors

import com.pavelperc.tutors.domain.*
import com.pavelperc.tutors.repo.CourseRepo
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
        private val subjectRepo: SubjectRepo,
        private val courseRepo: CourseRepo
) : ApplicationRunner {
    
    override fun run(args: ApplicationArguments) {
        
        // SETUP H2 DATABASE TO START A SERVER!!!!!!!! (for debug with intellij idea)
        org.h2.tools.Server.createTcpServer().start();
        
        
        // Subjects
        val (math, informatics, russian) = listOf(Subject("math"), Subject("informatics"), Subject("russian"))
        
        // our best tutors
        var sergey = Tutor("sergey", "sanya_belij@brigada.com", firstName = "Серёга", lastName = "Безруков")
        var ivan = Tutor("ivan", "super_tutor@hse.ru", firstName = "Просто Ваня")
        
        
        sergey.certificates.add(TutorCertificate(sergey, "lover", "Покоритель женских сердец."))
        
        ivan.subjects = mutableSetOf(math, informatics, russian)
        sergey.subjects.add(informatics)
        
        // our best students
        val pavel = Student("pavel", firstName = "Паша")
        val andrey = Student("andrey", firstName = "Дрюня")
        val katja = Student("katja", firstName = "Катя")
        
        
        subjectRepo.saveAll(listOf(math, russian, informatics))
        personRepo.saveAll(listOf(pavel, andrey, katja))
        ivan = personRepo.save(ivan)
        sergey = personRepo.save(sergey)
        
        
        val matan = Course("matan", math, mutableSetOf(ivan, sergey))
        val diskra = Course("diskra", informatics, mutableSetOf(sergey))
    
        courseRepo.saveAll(listOf(matan, diskra))
        // with updated courses
        personRepo.saveAll(listOf(ivan, sergey))
    
        listOf(courseRepo, subjectRepo, personRepo).forEach { it.flush() }
    }
}