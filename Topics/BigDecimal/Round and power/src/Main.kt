import java.math.BigDecimal
import java.math.RoundingMode     

fun main() {
    val power: Int = readLine()!!.toInt()
    val mode: Int = readLine()!!.toInt()
    val number: BigDecimal = readLine()!!.toBigDecimal()
    println(number.setScale(mode, RoundingMode.FLOOR).pow(power))
}