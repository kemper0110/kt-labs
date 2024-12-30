package org.example

import java.io.IOException

fun main() {
    val university = University()

    while (true) {
        println("\nВыберите команду:\n" +
                "1. Добавить студента\n" +
                "2. Добавить преподавателя\n" +
                "3. Добавить курс\n" +
                "4. Назначить студента курсу\n" +
                "5. Показать расписание\n" +
                "6. Показать информацию о персонах\n" +
                "0. Выход")

        try {
            val command = readln().toInt()
            when (command) {
                1 -> {
                    println("Введите имя, фамилию, дату рождения")
                    val name = readln()
                    val surname = readln()
                    val birthDate = readln()
                    val id = university.addStudent(name, surname, birthDate)
                    println("Студент $id добавлен")
                }
                2 -> {
                    println("Введите название департамента, имя, фамилию, дату рождения")
                    val department = readln()
                    val name = readln()
                    val surname = readln()
                    val birthDate = readln()
                    val id = university.addTeacher(department, name, surname, birthDate)
                    println("Преподаватель $id добавлен")
                }
                3 -> {
                    println("Введите название курса, идентификатор преподавателя")
                    val courseName = readln()
                    val teacherId = readln()
                    val id = university.addCourse(courseName, teacherId)
                    println("Курс $id добавлен")
                }
                4 -> {
                    println("Введите идентификатор студента, идентификатор курса")
                    val studentId = readln()
                    val courseId = readln()
                    university.assignStudentToCourse(studentId, courseId)
                    println("Курс $courseId назначен студенту $studentId")
                }
                5 -> {
                    university.showSchedule()
                }
                6 -> {
                    println("Введите идентификатор персоны")
                    val personId = readln()
                    university.showPersonInfo(personId)
                }
                0 -> {
                    println("Выход")
                    return
                }
            }
        } catch (e: NumberFormatException) {
            println("Неверный ввод")
        } catch (e: IOException) {
            println("Не удалось прочитать ввод")
        } catch (e: Exception) {
            println("Ошибка выполнения команды: $e")
        }
    }

}