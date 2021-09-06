fun main() {
    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    println(if (b == 0) "Division by zero, please fix the second argument!" else a / b)
}