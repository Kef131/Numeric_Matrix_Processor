package processor

import kotlin.math.pow

fun Number.str(): String = "The result is: \n$this\n"

data class Matrix(val sizeRow: Int, val sizeCol: Int, val matrix: Array<DoubleArray>) {
    override fun toString(): String {
        var matrixString = "The result is: \n"
        for (row in matrix) {
            for (element in row) {
                //Error trying to calculate Inverse matrix
                if (element.isInfinite() || element.isNaN())
                    return "This matrix doesn't have an inverse.\n"
                //Space for sign
                if (element >= 0) matrixString += " "
                //Formatting space for matrix
                matrixString +=
                    if (element % 1.0 == 0.0) "   " + element.toInt().toString()
                    else String.format("%.2f", element)
                matrixString += " "
            }
            matrixString += "\n"
        }
        return matrixString
    }

    fun scalarMultiplication(C: String): Matrix {
        val resultMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        val constantC = C.toDouble()
        for (i in 0 until sizeRow)
            for (j in 0 until sizeCol)
                resultMatrix.matrix[i][j] += matrix[i][j] * constantC
        return resultMatrix
    }

    fun matrixTranspose(optionTranspose: String): Matrix {
        return when (optionTranspose) {
            "1" -> mainDiagonal()
            "2" -> sideDiagonal()
            "3" -> verticalLine()
            "4" -> horizontalLine()
            else -> Matrix(0, 0, Array(0) { DoubleArray(0) })
        }
    }

    val mainDiagonal = {
        val newMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        for (i in 0 until sizeRow) {
            for (j in 0 until sizeCol) {
                newMatrix.matrix[j][i] = matrix[i][j]
            }
        }
        newMatrix
    }
    val sideDiagonal = {
        val newMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        for (i in 0 until sizeCol) {
            var counter = sizeRow - (i + 1)
            for (j in 0 until sizeRow) {
                newMatrix.matrix[i][j] = matrix[i + counter][j + counter]
                counter--
            }
        }
        newMatrix
    }
    val verticalLine = {
        val newMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        for (i in 0 until sizeCol) {
            var counter = sizeRow - 1
            for (j in 0..matrix.lastIndex) {
                newMatrix.matrix[i][j] = matrix[i][counter]
                counter--
            }
        }
        newMatrix
    }
    val horizontalLine = {
        val newMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        for (i in 0 until sizeRow) {
            var counter = sizeCol - 1
            for (j in 0 until sizeCol) {
                newMatrix.matrix[j][i] = matrix[counter][i]
                counter--
            }
        }
        newMatrix
    }

    fun matrixSum(B: Matrix): Matrix {
        val resultMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        for (i in 0 until sizeRow)
            for (j in 0 until sizeCol)
                resultMatrix.matrix[i][j] += matrix[i][j] + B.matrix[i][j]
        return resultMatrix
    }

    fun matrixProductDot(B: Matrix): Matrix {
        val sizeRow = this.sizeRow
        val sizeCol = B.sizeCol
        val sizeM = this.sizeCol
        val resultMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        // Traverse each row in A
        for (idxRow in 0 until sizeRow) {
            // Traverse for each column in B
            for (idxCol in 0 until sizeCol) {
                // Compute the multiplication and add it to sum for [idxRow][idxCol]
                var sum = 0.0
                //Number of multiplications to add in the sum
                for (i in 0 until sizeM)
                    sum += matrix[idxRow][i] * B.matrix[i][idxCol]
                resultMatrix.matrix[idxRow][idxCol] += sum
            }
        }
        return resultMatrix
    }


    private fun shrinkMatrix(row: Int, col: Int): Matrix {
        val sizeShrunkRow: Int = sizeRow - 1
        val sizeShrunkCol = sizeCol - 1
        val newShrunkMatrix = Matrix(sizeShrunkRow, sizeShrunkCol, Array(sizeShrunkRow) { DoubleArray(sizeShrunkCol) })
        var index = 0
        for (i in 0..matrix.lastIndex) {
            if (i == row) continue
            val rowList = mutableListOf<Double>()
            for (j in 0..matrix[i].lastIndex) {
                if (j == col) continue
                else rowList.add(matrix[i][j])
            }
            newShrunkMatrix.matrix[index] = rowList.toDoubleArray()
            index++
        }
        return newShrunkMatrix
    }

    fun matrixDeterminant(): Number {
        fun returnIntOrDouble(number: Double): Number = if (number % 1.0 == 0.0) number.toInt() else number

        var sum = 0.0
        //Base case: Determinant of square matrix 2 X 2 or 1 x 1
        if (matrix.size == 1 && matrix[0].size == 1)
            return matrix[0][0]
        else if (matrix.size == 2 && matrix[0].size == 2)
            return returnIntOrDouble(matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0])
        //Recursive case: this sign and get cofactor of Xij
        else
            for (i in 0 until sizeRow)
                sum += (-1.0).pow(1 + (i + 1)) * matrix[0][i] * this.shrinkMatrix(0, i).matrixDeterminant().toDouble()
        return returnIntOrDouble(sum)
    }

    fun matrixInverse(): Matrix {
        val inverseMatrix =
            this.cofactorMatrix().matrixTranspose("1")
                .scalarMultiplication((1 / this.matrixDeterminant().toDouble()).toString()).matrix
        return Matrix(sizeRow, sizeCol, inverseMatrix)
    }

    private fun cofactorMatrix(): Matrix {
        fun sign(row: Int, col: Int): Int = if ((row + col) % 2 == 0) 1 else -1

        val cofactorMatrix = Matrix(sizeRow, sizeCol, Array(sizeRow) { DoubleArray(sizeCol) })
        for (i in 0 until sizeRow)
            for (j in 0 until sizeCol)
                cofactorMatrix.matrix[i][j] = sign(i, j) * this.shrinkMatrix(i, j).matrixDeterminant().toDouble()
        return cofactorMatrix
    }
}
