package com.pavelperc.tutors.domain

import javax.persistence.*

// https://grokonez.com/spring-framework/spring-data/kotlin-springjpa-hibernate-one-many-relationship


@Entity
data class TutorCertificate(
        @ManyToOne(targetEntity = Tutor::class)
        @JoinColumn(name = "tutor_id", updatable = false)
        val tutor: Tutor,
        
        @Column(nullable = false)
        var name: String,

        @Column(nullable = false)
        var description: String = "",
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
) {
    
}