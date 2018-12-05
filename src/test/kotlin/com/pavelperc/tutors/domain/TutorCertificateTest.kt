package com.pavelperc.tutors.domain

import com.pavelperc.tutors.repo.PersonRepo
import com.pavelperc.tutors.repo.StudentRepo
import com.pavelperc.tutors.repo.TutorRepo
import org.amshove.kluent.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
internal class TutorCertificateTest @Autowired constructor(
        val personRepo: PersonRepo,
        val studentRepo: StudentRepo,
        val tutorRepo: TutorRepo
) {
    
    @Test
    fun insertCertificate() {
        personRepo.save(Tutor("tutor1"))
        
        var tutor = tutorRepo.findByLogin("tutor1").shouldNotBeNull()
        tutor.certificates.add(TutorCertificate(tutor, "c1", ""))
        
        tutorRepo.save(tutor)
        
        // request the tutor again
        tutor = tutorRepo.findByLogin("tutor1").shouldNotBeNull()
        
        val certificate = tutor.certificates.shouldNotBeEmpty()[0]
        certificate.tutor.shouldNotBeNull() shouldEqual tutor
    }
}