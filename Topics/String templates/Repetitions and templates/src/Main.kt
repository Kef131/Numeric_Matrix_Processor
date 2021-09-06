fun main() {
    val word = readLine()!!
    print("${word.length} repetitions of the word $word: ")
    repeat(word.length) {
        print(word)
    }
}