package org.example

import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.*

fun fromConsole(scanner: Scanner): Pair<Matrix, Matrix> {
    println("Введите первую матрицу")
    val matrix1 = readMatrix(scanner)
    println("Введите вторую матрицу")
    val matrix2 = readMatrix(scanner)
    return Pair(matrix1, matrix2)
}

fun fromFile(scanner: Scanner): Pair<Matrix, Matrix> {
    val matrix1 = readMatrix(scanner)
    val matrix2 = readMatrix(scanner)
    return Pair(matrix1, matrix2)
}

fun main(args: Array<String>) {
    var inputStream: InputStream? = null
    args.forEach {
        when {
            it.startsWith("--input-file=") -> {
                try {
                    inputStream = File(it.substring("--input-file=".length)).inputStream()
                } catch (e: Exception) {
                    println("Не удалось открыть файл")
                }
            }
        }
    }


    try {
        val (matrix1, matrix2) = if (inputStream == null) {
            println("Чтение матриц из консоли")
            fromConsole(Scanner(System.`in`))
        } else {
            println("Чтение матриц из файла")
            // TODO: а что за проблемы с выводом типа?
            fromFile(Scanner(inputStream!!))
        }

        val result = matrix1.multiply(matrix2)
        result.printMatrix(System.`out`)
    } catch (e: Exception) {
        println("Продолжить выполнение невозможно: $e")
    }
}