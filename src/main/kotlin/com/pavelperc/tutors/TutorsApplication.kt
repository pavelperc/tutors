package com.pavelperc.tutors

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TutorsApplication

fun main(args: Array<String>) {
    runApplication<TutorsApplication>(*args)
}
