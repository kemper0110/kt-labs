package org.example

class Student(val studentId: String, val coursesEnrolled: MutableList<Course>, name: String, surname: String, birthDate: String) :
    Person(name, surname, birthDate) {

}