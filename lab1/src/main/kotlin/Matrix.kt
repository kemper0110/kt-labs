package org.example

class Matrix {
    private val data: DoubleArray
    val rows: Int
    val cols: Int

    constructor(rows: Int, cols: Int) {
        this.rows = rows
        this.cols = cols
        this.data = DoubleArray(rows * cols)
    }

    constructor(rows: Int, cols: Int, data: DoubleArray) : this(rows, cols) {
        data.copyInto(this.data, 0, 0, rows * cols)
    }

    operator fun get(i: Int, j: Int) = data[i * cols + j]
    operator fun set(i: Int, j: Int, value: Double) {
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

fun Matrix.multiply(matrix: Matrix): Matrix {
    require(this.cols == matrix.rows) { "Количество столбцов первой матрицы должно совпадать с количеством строк второй матрицы" }
    val result = Matrix(this.rows, matrix.cols)
    for (i in 0 until this.rows) {
        for(j in 0 until matrix.cols) {
            var sum = .0
            for (k in 0 until this.cols)
                sum += this[i, k] * matrix[k, j]
            result[i, j] = sum
        }
    }
    return result
}