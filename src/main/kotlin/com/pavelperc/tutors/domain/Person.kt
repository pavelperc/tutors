package com.pavelperc.tutors.domain

import javax.persistence.*


@Entity(name = "Persons")
data class Person(
        @Column(unique = true, nullable = false)
        var login: String,
        
        var email: String? = null,
        
        @Column(name = "first_name")
        var firstName: String? = null,
        
        @Column(name = "middle_name")
        var middleName: String? = null,
        
        @Column(name = "last_name")
        var lastName: String? = null,
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
) {
    
    val fullName: String
        get() = "${firstName?:""} ${middleName?:""} ${lastName?:""}".trim()
}