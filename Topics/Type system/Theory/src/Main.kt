fun getLength(input: String?): Int = input?.length ?: -1
fun main(args: Array<String>) {
  println(getLength(null))
}
