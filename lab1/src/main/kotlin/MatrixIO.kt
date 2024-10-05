package org.example

import java.util.*

fun readMatrix(scanner: Scanner): Matrix {
    val rows = scanner.nextInt()
    val cols = scanner.nextInt()
    val matrix = Matrix(rows, cols)
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            matrix[i, j] = scanner.nextInt()
        }
    }
    return matrix
}

fun Matrix.printMatrix(outputStream: java.io.OutputStream) {
    for (i in 0 until this.rows) {
        for (j in 0 until this.cols) {
            outputStream.write(this[i, j].toString().toByteArray())
            outputStream.write(" ".toByteArray())
        }
        outputStream.write("\n".toByteArray())
    }
}