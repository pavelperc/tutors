package com.pavelperc.tutors

import com.pavelperc.tutors.domain.*
import com.pavelperc.tutors.repo.*
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.LocalTime
import javax.transaction.Transactional


@Component
class DataLoader(
        // autowired by default
        private val personRepo: PersonRepo,
        private val tutorRepo: TutorRepo,
        private val subjectRepo: SubjectRepo,
        private val courseRepo: CourseRepo,
        private val scheduleRepo: ScheduleRepo,
        private val tutorScheduleRepo: TutorScheduleRepo
) : ApplicationRunner {

    @Transactional // FIXES lazy initialization of tutorSchedules!!
    override fun run(args: ApplicationArguments) {
        
        // SETUP H2 DATABASE TO START A SERVER!!!!!!!! (for debug with intellij idea)
        org.h2.tools.Server.createTcpServer().start();
        
        
        // Subjects
        val (math, informatics, russian, spanish) = listOf(Subject("math"), Subject("informatics"),
                Subject("russian"), Subject("spanish"))
        
        // our best tutors
        var sergey = Tutor("sergey", "sanya_belij@brigada.com", firstName = "Серёга", lastName = "Безруков")
        var ivan = Tutor("ivan", "super_tutor@hse.ru", firstName = "Просто Ваня")
        
        
        sergey.certificates.add(TutorCertificate(sergey, "lover", "Покоритель женских сердец."))
        
        ivan.subjects = mutableSetOf(math, informatics, russian)
        sergey.subjects.add(informatics)
        sergey.subjects.add(spanish)

        // our best students
        val pavel = Student("pavel", firstName = "Паша")
        val andrey = Student("andrey", firstName = "Дрюня")
        val katja = Student("katja", firstName = "Катя")
        
        
        subjectRepo.saveAll(listOf(math, russian, informatics, spanish))
        personRepo.saveAll(listOf(pavel, andrey, katja))
        ivan = personRepo.save(ivan)
        sergey = personRepo.save(sergey)
        
        
        var matan = Course("matan", math)
        matan.connectTutors(ivan, sergey)
        
        var diskra = Course("diskra", informatics)
        diskra.connectTutors(sergey)

        var expressSpanish = Course("expressSpanish", spanish)
        expressSpanish.connectTutors(sergey)
        
        matan = courseRepo.save(matan)
        diskra = courseRepo.save(diskra)
        expressSpanish = courseRepo.save(expressSpanish)

        // with updated courses
        ivan = personRepo.save(ivan)
        sergey = personRepo.save(sergey)


        // --------SETUP SCHEDULES


        val schedules = loadSchedules()

        addTutorSchedules(sergey, schedules.filter { it.day == Day.Sunday })
        addTutorSchedules(ivan, schedules.filter { it.day != Day.Monday && it.day != Day.Tuesday })

//        println("TUTOR_SCHEDULES_SERGEY: " + sergey.tutorSchedules)

        sergey.tutorSchedules.byDayTime(Day.Sunday, 12).apply {
            course = expressSpanish
            student = katja
            tutorScheduleRepo.save(this)
        }
        sergey.tutorSchedules.byDayTime(Day.Sunday, 13).apply {
            course = expressSpanish
            student = katja
            tutorScheduleRepo.save(this)
        }

        sergey.tutorSchedules.byDayTime(Day.Sunday, 14).apply {
            course = diskra
            student = katja
            tutorScheduleRepo.save(this)
        }

        sergey = tutorRepo.save(sergey)
        ivan = tutorRepo.save(ivan)


    }

    private fun addTutorSchedules(tutor: Tutor, schedules: List<Schedule> ) {
        schedules
                .map { TutorSchedule(tutor, it) }
                .map {
                    tutorScheduleRepo.save(it)
                }
                .also { tutor.tutorSchedules.addAll(it) }
    }



    private fun Set<TutorSchedule>.byDayTime(day: Day, hour: Int): TutorSchedule {
        return this.find { it.schedule.day == day && it.schedule.startTime.hour == hour }!!
    }

    private fun loadSchedules(): List<Schedule> {

        val schedules = mutableListOf<Schedule>()

        for (day in Day.values()) {
            for (hour in 10..20) {
                if ((day == Day.Saturday || day == Day.Sunday) && (hour > 16 || hour < 12)) {
                    continue
                }
                var schedule = Schedule(day, LocalTime.of(hour, 0), LocalTime.of(hour + 1, 0))
                schedule = scheduleRepo.save(schedule)
//                println("saved schedule: $schedule")
                schedules.add(schedule)
            }
        }

        return schedules
    }
}