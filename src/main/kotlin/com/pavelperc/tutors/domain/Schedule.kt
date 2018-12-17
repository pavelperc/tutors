package com.pavelperc.tutors.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalTime
import javax.persistence.*


/**
 * Time gap, which can be booked by student for some teacher.
 */
@Table(
        uniqueConstraints=[UniqueConstraint(columnNames=["day", "startTime", "endTime"])]
)
@Entity
open class Schedule(

        @Enumerated(EnumType.STRING)
        open var day: DayOfWeek,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        open var startTime: LocalTime,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        open var endTime: LocalTime
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open val id: Long = 0


    @OneToMany(mappedBy = "schedule", cascade = arrayOf(CascadeType.REMOVE), fetch = FetchType.LAZY)
    open val tutorSchedules: Set<TutorSchedule> = mutableSetOf()


    open val numberOfTutors: Int
        get() = tutorSchedules.size




    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Schedule) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}