import java.math.BigDecimal

fun main(args: Array<String>) {
    val dwalinOreWeight = readLine()!!.toBigDecimal()
    val balinOreWeight = readLine()!!.toBigDecimal()
    val thorinOreWeight = readLine()!!.toBigDecimal()
    println(dwalinOreWeight + balinOreWeight + thorinOreWeight)
}