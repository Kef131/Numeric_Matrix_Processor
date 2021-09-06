data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(other: Any?): Boolean {
        other as Client
        return name == other.name && age == other.age
    }
}
fun main() {
    val client1 = Client(readLine()!!, readLine()!!.toInt(), readLine()!!.toInt())
    val client2 = Client(readLine()!!, readLine()!!.toInt(), readLine()!!.toInt())
    println(client1 == client2)
}