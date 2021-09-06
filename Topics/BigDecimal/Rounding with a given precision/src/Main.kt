import java.math.RoundingMode

fun main() {             
    val number = readLine()!!.toBigDecimal()
    val newScale = readLine()!!.toInt()
    println(number.setScale(newScale, RoundingMode.HALF_DOWN))
}
