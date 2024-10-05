package org.example

class Matrix {
    private val data: IntArray
    val rows: Int
    val cols: Int

    constructor(rows: Int, cols: Int) {
        this.rows = rows
        this.cols = cols
        this.data = IntArray(rows * cols)
    }

    constructor(rows: Int, cols: Int, data: IntArray) : this(rows, cols) {
        data.copyInto(this.data, 0, 0, rows * cols)
    }

    operator fun get(i: Int, j: Int) = data[i * cols + j]
    operator fun set(i: Int, j: Int, value: Int) {
        data[i * cols + j] = value
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Matrix
        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        var result = rows
        result = 31 * result + cols
        result = 31 * result + data.contentHashCode()
        return result
    }
}

fun Matrix.multiplyTransposed(transposed: Matrix): Matrix {
    val result = Matrix(this.rows, transposed.rows)
    for (i in 0 until this.rows) {
        for(j in 0 until transposed.rows) {
            var sum = 0
            for (k in 0 until this.cols)
                sum += this[i, k] * transposed[j, k]
            result[i, j] = sum
        }
    }
    return result
}

fun Matrix.transposed(): Matrix {
    val transposed = Matrix(this.cols, this.rows)
    for (i in 0 until this.rows)
        for (j in 0 until this.cols)
            transposed[j, i] = this[i, j]
    return transposed
}
