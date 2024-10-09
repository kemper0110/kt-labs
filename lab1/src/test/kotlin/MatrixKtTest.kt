import org.example.Matrix
import org.example.multiply
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Scanner

class MatrixKtTest {
    @Test
    fun readMatrix() {
        val inputStream = "2 2\n3 4\n5 6".byteInputStream()
        val matrix = org.example.readMatrix(Scanner(inputStream))
        val expected = Matrix(2, 2, doubleArrayOf(3.0, 4.0, 5.0, 6.0))
        assertEquals(expected, matrix)
    }

    @Test
    fun multiplyQuadratic() {
        val matrix1 = Matrix(2, 2, doubleArrayOf(3.0, 4.0, 5.0, 6.0))
        val matrix2 = Matrix(2, 2, doubleArrayOf(1.0, 2.0, 3.0, 4.0))
        val expected = Matrix(2, 2, doubleArrayOf(15.0, 22.0, 23.0, 34.0))
        val result = matrix1.multiply(matrix2)
        assertEquals(expected, result)
    }

    @Test
    fun multiply() {
        val matrix1 = Matrix(2, 3, doubleArrayOf(3.0, 4.0, 5.0, 6.0, 7.0, 8.0))
        val matrix2 = Matrix(3, 1, doubleArrayOf(1.0, 3.0, 5.0))
        val expected = Matrix(2, 1, doubleArrayOf(40.0, 67.0))
        val result = matrix1.multiply(matrix2)
        assertEquals(expected, result)
    }
}