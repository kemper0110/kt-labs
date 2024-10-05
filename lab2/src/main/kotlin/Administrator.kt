package org.example

class Administrator(responsibilities: List<String>, employeeId: String, department: String, name: String, surname: String, birthDate: String) :
    Staff(employeeId, department, name, surname, birthDate) {
}