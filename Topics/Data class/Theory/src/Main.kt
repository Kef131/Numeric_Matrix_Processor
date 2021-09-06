//data class Box(val height: Int, val length: Int, val width: Int) {
//    var size: Int = height + length + width
//    override fun toString(): String = "Boc(height=$height, width=$width, size=$size)"
//}
//fun main(args: Array<String>) {
//    println(Box(3,21,6))
//}
// do not change this data class
data class Box(val height: Int, val length: Int, val width: Int)

fun main() {
    val (height, length, length2, width) = IntArray(4) { readLine()!!.toInt() }
    val box1 = Box(height, length, width)
    println(box1)
    val box2 = box1.copy(length = length2)
    println(box2)
}