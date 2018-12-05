package com.pavelperc.tutors.domain

import javax.persistence.*


@Entity
data class Subject(
        @Column(nullable = false, updatable = false, unique = true)
        val name: String,

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
) {
}