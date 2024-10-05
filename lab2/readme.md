Разработайте иерархию классов для моделирования структуры университета.

Базовый класс Person:

- Свойства: name, surname, birthDate.
- Методы: displayInfo().

Наследники:

- Student: свойства studentId, coursesEnrolled.
- Staff: свойства employeeId, department.

От Staff наследуются:

- Teacher: свойства subjectsTaught, методы assignGrade().
- Administrator: свойства responsibilities.

Класс Course с свойствами courseId, courseName, teacher, studentsEnrolled.

Класс University управляет коллекциями Person и Course, предоставляет методы для зачисления студентов, назначения
преподавателей, формирования расписания.

Консольное приложение должно позволять:

- Добавлять студентов и сотрудников.
- Создавать курсы и назначать преподавателей.
- Записывать студентов на курсы.
- Отображать расписание и информацию о пользователях.