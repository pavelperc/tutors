package com.pavelperc.tutors.domain

import javax.persistence.*


// https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/

@Entity(name = "Persons")
@Inheritance(strategy = InheritanceType.JOINED)
sealed class Person(
        @Column(unique = true, nullable = false)
        open var login: String,
        
        var email: String? = null,
        
        @Column(name = "first_name")
        var firstName: String? = null,
        
        @Column(name = "middle_name")
        var middleName: String? = null,
        
        @Column(name = "last_name")
        var lastName: String? = null
) {
    val fullName: String
        get() = listOfNotNull(firstName, middleName, lastName).joinToString(" ")
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    
    override fun toString() =
            "Person(login='$login', email=$email, firstName=$firstName, middleName=$middleName, lastName=$lastName, id=$id)"
    
    
}

@Entity(name = "Tutors")
class Tutor(
        login: String,
        email: String? = null,
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
) : Person(login, email, firstName, middleName, lastName) {
    
    
    override fun toString() = "Tutor: ${super.toString()}"
}

@Entity(name = "Students")
class Student(
        login: String,
        email: String? = null,
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
) : Person(login, email, firstName, middleName, lastName) {
    
    override fun toString() = "Student: ${super.toString()}"
}














