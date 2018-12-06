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
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Person) return false
    
        if (id != other.id) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        return id.hashCode()
    }
    
    
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
    
    
    @ManyToMany(targetEntity = Course::class, fetch = FetchType.EAGER)
    var courses: MutableSet<Course> = mutableSetOf()
    
    
    override fun toString() = "Tutor: ${super.toString()}"
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as Tutor
        
        if (id != other.id) return false
        return true
    }
    
    override fun hashCode(): Int {
        return id.hashCode()
    }
    
    
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
    
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as Student
        
        if (id != other.id) return false
        return true
    }
    
    override fun hashCode(): Int {
        return id.hashCode()
    }
}














