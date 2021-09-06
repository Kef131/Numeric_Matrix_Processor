import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val listInts = mutableListOf<Int>()
    for (i in 1..6) {
        listInts.add(scanner.nextInt())
    }
    print("${listInts[0]}:${listInts[1]}:${listInts[2]} ${listInts[3]}/${listInts[4]}/${listInts[5]}")
}