import java.math.RoundingMode

fun main() {
    val startingAmount = readLine()!!.toBigDecimal()
    val yearlyPercent = readLine()!!.toBigDecimal()
    val years = readLine()!!.toInt()
    val result = startingAmount * (1.toBigDecimal() +
            yearlyPercent.divide(100.toBigDecimal(), 2, RoundingMode.CEILING)).pow(years)
    print("Amount of money in the account: ${result.setScale(2,RoundingMode.CEILING)}")
}