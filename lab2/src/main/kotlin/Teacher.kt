package org.example

class Teacher(subjectsTaught: List<String>, employeeId: String, department: String, name: String, surname: String, birthDate: String) :
    Staff(employeeId, department, name, surname, birthDate) {
    fun assignGrade() {
        println("Teacher $name $surname has been assigned a grade!")
    }
}