package com.pavelperc.tutors.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*


@Table(
        uniqueConstraints=[UniqueConstraint(columnNames=["tutor_id", "schedule_id"])]
)
@Entity
open class TutorSchedule(
        @ManyToOne(targetEntity = Tutor::class, fetch = FetchType.LAZY)
        @JoinColumn(name = "tutor_id", updatable = false, nullable = false)
        @JsonIgnore
        val tutor: Tutor,

        @ManyToOne(targetEntity = Schedule::class, fetch = FetchType.EAGER)
        @JoinColumn(name = "schedule_id", updatable = false, nullable = false)
        val schedule: Schedule
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open val id: Long = 0

    @ManyToOne(targetEntity = Student::class)
    @JoinColumn(name = "student_id", updatable = true, nullable = true)
    @JsonIgnore
    open var student: Student? = null

    val studentLogin: String?
        get() = student?.login


    @ManyToOne(targetEntity = Course::class)
    @JoinColumn(name = "course_id", updatable = true, nullable = true)
    open var course: Course? = null

    val isEmpty: Boolean
        get() = student == null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TutorSchedule) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "TutorSchedule(tutor=${tutor.login}, schedule=$schedule, id=$id, student=${student?.login}, course=${course?.name})"
    }


}