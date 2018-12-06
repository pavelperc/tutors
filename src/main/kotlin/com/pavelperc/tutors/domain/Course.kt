package com.pavelperc.tutors.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreType
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Entity
data class Course(
        @Column(unique = true, nullable = false)
        var name: String,
        
        @ManyToOne()
        var subject: Subject,
        
        @ManyToMany(mappedBy = "courses")
        @JsonIgnore
        var tutors: MutableSet<Tutor> = mutableSetOf(),
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
        
) {
    init {
        tutors.forEach { it.courses.add(this) }
    }
    
    
    override fun toString(): String {
        return "Course(name='$name', subject=${subject.name}, tutors=${tutors.map { it.login }}, id=$id)"
    }
}