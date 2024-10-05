package org.example

open class Person(val name: String, val surname: String, val birthDate: String) {
    fun displayInfo() {
        val a = arrayListOf('a', 'b', 'c')
        println("Name: $name, surname: $surname, birthDate: $birthDate")
    }
}