fun parseCardNumber(cardNumber: String): Long {
    val numberTrimmed = cardNumber.replace("\\s+".toRegex(), "")
    if (cardNumber.split(" ").size != 4 ||
        numberTrimmed.firstOrNull { it.isLetter() } != null ||
        numberTrimmed.length > 16) {
        throw Exception("Error")
    }
    return numberTrimmed.toLong()
}