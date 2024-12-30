package org.example

import org.example.utils.IdSequence

class University {
    private val teacherIdSequence = IdSequence("teacher")
    private val studentIdSequence = IdSequence("student")
    private val courseIdSequence = IdSequence("course")
    private val staff = mutableListOf<Staff>()
    private val students = mutableListOf<Student>()
    private val courses = mutableListOf<Course>()

    fun addStudent(name: String, surname: String, birthDate: String): String {
        Student(studentIdSequence.next(), mutableListOf(), name, surname, birthDate).also {
            students.add(it)
            return it.studentId
        }
    }

    fun addTeacher(department: String, name: String, surname: String, birthDate: String): String {
        Teacher(listOf(), teacherIdSequence.next(), department, name, surname, birthDate).also {
            staff.add(it)
            return it.employeeId
        }
    }

    fun addCourse(courseName: String, teacherId: String): String {
        val teacher = staff.find { it.employeeId == teacherId } as Teacher
        Course(courseIdSequence.next(), courseName, teacher, mutableListOf()).also {
            courses.add(it)
            return it.courseId
        }
    }

    fun assignStudentToCourse(studentId: String, courseId: String) {
        val course = courses.find { it.courseId == courseId } as Course
        val student = students.find { it.studentId == studentId } as Student
        course.studentsEnrolled.add(student)
        student.coursesEnrolled.add(course)
    }

    fun showSchedule() {
        courses.forEach {
            println("${it.courseName}[${it.courseId}]: ${it.teacher.name} ${it.teacher.surname}")
        }
        println()
    }

    fun showPersonInfo(personId: String) {
        staff.find { it.employeeId == personId }?.displayInfo()
            ?: students.find { it.studentId == personId }?.displayInfo()
    }
}