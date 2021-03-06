package com.pavelperc.tutors.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreType
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.*


@Entity
open class Course(
        @Column(unique = true, nullable = false)
        open var name: String,
        
        @ManyToOne()
        open var subject: Subject
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open val id: Long = 0
    
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    open val tutors: Set<Tutor> = mutableSetOf()
    
    open val tutorIds: Set<Long>
        get() = tutors.map { it.id }.toSet()
    
    
    fun connectTutors(vararg tutors: Tutor) = connectTutors(tutors.asList())
    
    fun connectTutors(tutors: Collection<Tutor>) {
        (this.tutors as MutableSet<Tutor>).addAll(tutors)
        tutors.forEach { tutor ->
            tutor.courses.add(this)
        }
    }
    
    fun disconnectTutors(vararg tutors: Tutor) = disconnectTutors(tutors.asList())
    
    fun disconnectTutors(tutors: Collection<Tutor>) {
        (this.tutors as MutableSet<Tutor>).removeAll(tutors)
        
        tutors.forEach { tutor ->
            tutor.courses.remove(this)
        }
//        println("After removal: ${tutors.map { "${it.login} :${it.courses.map { it.name }}" }}")
    }

//
//    @OneToMany(mappedBy = "course", cascade = arrayOf(CascadeType.REMOVE), fetch = FetchType.LAZY)
//    @JsonIgnore
//    open val tutorSchedules: Set<TutorSchedule> = mutableSetOf()





    override fun toString(): String {
        return "Course(name='$name', subject=${subject.name}, tutors=${tutors.map { it.login }}, id=$id)"
    }

    /** Compares just id, name and subject*/
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as Course
        
        if (name != other.name) return false
        if (subject != other.subject) return false
        if (id != other.id) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + subject.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}