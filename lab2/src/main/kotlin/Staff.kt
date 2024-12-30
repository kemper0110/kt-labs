package org.example

open class Staff(val employeeId: String, val department: String, name: String, surname: String, birthDate: String) :
    Person(name, surname, birthDate) {
}