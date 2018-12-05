package com.pavelperc.tutors.domain

import javax.persistence.*


// https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/

// why we need open on every property:
// https://stackoverflow.com/questions/37733629/kotlin-with-jpa-hibernate-no-lazy-loading-without-open

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
sealed class Person(
        @Column(unique = true, nullable = false)
        open var login: String,
        
        open var email: String? = null,
        
        open var firstName: String? = null,
        
        open var middleName: String? = null,
        
        open var lastName: String? = null
) {
    val fullName: String
        get() = listOfNotNull(firstName, middleName, lastName).joinToString(" ")
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    
    override fun toString() =
            "Person(login='$login', email=$email, firstName=$firstName, middleName=$middleName, lastName=$lastName, id=$id)"
    
    
}

@Entity
class Tutor(
        login: String,
        email: String? = null,
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
) : Person(login, email, firstName, middleName, lastName) {
    
    @OneToMany(mappedBy = "tutor", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var certificates: MutableList<TutorCertificate> = mutableListOf()
    
    // why we should prefer sets in many to many:
    // https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
    
    @ManyToMany(targetEntity = Subject::class, fetch = FetchType.EAGER)
    var subjects: MutableSet<Subject> = mutableSetOf()
    
    
    override fun toString() = "Tutor: ${super.toString()}"
}

@Entity
class Student(
        login: String,
        email: String? = null,
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
) : Person(login, email, firstName, middleName, lastName) {
    
    override fun toString() = "Student: ${super.toString()}"
}














