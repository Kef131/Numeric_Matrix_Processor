package processor

fun getMatrix(ordinal: String = ""): Matrix {
    print("Enter size of$ordinal matrix: ")
    val (sizeRow, sizeCol) = readLine()!!.split(" ").map { it.toInt() }
    println("Enter$ordinal matrix: ")
    val matrix = Array(sizeRow) { readLine()!!.split(" ").map { it.toDouble() }.toDoubleArray() }
    return Matrix(sizeRow, sizeCol, matrix)
}

fun menu() {
    print(
        """1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
5. Calculate a determinant
6. Inverse matrix
0. Exit
Your choice: """
    )
}

fun menuTranspose() {
    print(
        """
1. Main diagonal
2. Side diagonal
3. Vertical line
4. Horizontal line
Your choice: """
    )
}

fun main() {
    menu()
    var option: String
    while (readLine()!!.also { option = it } != "0") {
        when (option) {
            "1" -> {
                val matrixA = getMatrix(" first")
                val matrixB = getMatrix(" second")
                if (matrixA.sizeRow == matrixB.sizeRow && matrixA.sizeCol == matrixB.sizeCol) {
                    println(matrixA.matrixSum(matrixB))
                } else {
                    println("The operation cannot be performed.\n")
                }
                menu()
            }
            "2" -> {
                val matrixA = getMatrix()
                print("Enter constant: ")
                val c = readLine()!!
                println(matrixA.scalarMultiplication(c))
                menu()
            }
            "3" -> {
                val matrixA = getMatrix(" first")
                val matrixB = getMatrix(" second")
                if (matrixA.sizeCol == matrixB.sizeRow) {
                    println(matrixA.matrixProductDot(matrixB))
                } else {
                    println("The operation cannot be performed.\n")
                }
                menu()
            }
            "4" -> {
                menuTranspose()
                val optionTranspose = readLine()!!
                val matrixA = getMatrix()
                println(matrixA.matrixTranspose(optionTranspose))
                menu()
            }
            "5" -> {
                val matrixA = getMatrix()
                println(matrixA.matrixDeterminant().str())
                menu()
            }
            "6" -> {
                val matrixA = getMatrix()
                println(matrixA.matrixInverse())
                menu()
            }
        }
    }
}