import org.example.Matrix
import org.example.multiplyTransposed
import org.example.transposed
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Scanner

class MatrixKtTest {
    @Test
    fun readMatrix() {
        val inputStream = "2 2\n3 4\n5 6".byteInputStream()
        val matrix = org.example.readMatrix(Scanner(inputStream))
        val expected = Matrix(2, 2, intArrayOf(3, 4, 5, 6))
        assertEquals(expected, matrix)
    }

    @Test
    fun transposed() {
        val matrix = Matrix(2, 2, intArrayOf(3, 4, 5, 6))
        val transposed = matrix.transposed()
        val expected = Matrix(2, 2, intArrayOf(3, 5, 4, 6))
        assertEquals(expected, transposed)
    }

    @Test
    fun doubleTransposed() {
        val matrix = Matrix(2, 2, intArrayOf(3, 4, 5, 6))
        val doubleTransposedMatrix = matrix.transposed().transposed()
        assertEquals(matrix, doubleTransposedMatrix)
    }

    @Test
    fun multiplyTransposedQuadratic() {
        val matrix1 = Matrix(2, 2, intArrayOf(3, 4, 5, 6))
        val matrix2 = Matrix(2, 2, intArrayOf(1, 2, 3, 4))
        val expected = Matrix(2, 2, intArrayOf(15, 22, 23, 34))
        val result = matrix1.multiplyTransposed(matrix2.transposed())
        assertEquals(expected, result)
    }

    @Test
    fun multiplyTransposed() {
        val matrix1 = Matrix(2, 3, intArrayOf(3, 4, 5, 6, 7, 8))
        val matrix2 = Matrix(3, 1, intArrayOf(1, 3, 5))
        val expected = Matrix(2, 1, intArrayOf(40, 67))
        val result = matrix1.multiplyTransposed(matrix2.transposed())
        assertEquals(expected, result)
    }
}