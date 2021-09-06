fun main() {
    val productType = readLine()!!
    val price = readLine()!!.toInt()
    val product = when (productType) {
        "headphones" -> Headphones(price)
        "smartphone" -> Smartphone(price)
        "tv" -> Tv(price)
        "laptop" -> Laptop(price)
        else -> Product(price)
    }
    println(totalPrice(product))
}

fun totalPrice(product: Product) = (product.price.toDouble() +
        product.price.toDouble() * (product.tax / 100.0)).toInt()

open class Product(val price: Int, val tax: Int = 0)
class Headphones(price: Int, tax: Int = 11) : Product(price, tax)
class Smartphone(price: Int, tax: Int = 15) : Product(price, tax)
class Tv(price: Int, tax: Int = 17) : Product(price, tax)
class Laptop(price: Int, tax: Int = 19) : Product(price, tax)