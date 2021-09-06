import kotlin.math.roundToInt

fun main() {
    val rooms = readLine()!!.toInt()
    val price = readLine()!!.toInt()
    val house = House(rooms, price)
    println(totalPrice(house))
}

fun totalPrice(house: House): Any? {
    val currentHouse: SubTypeHouse = when {
        house.rooms == 1 -> Cabin(house.rooms, house.price)
        house.rooms in 2..3 -> Bungalow(house.rooms, house.price)
        house.rooms == 4 -> Cottage(house.rooms, house.price)
        house.rooms in 5..7 -> Mansion(house.rooms, house.price)
        house.rooms in 8..10 -> Palace(house.rooms, house.price)
        house.rooms < 1 -> Cabin(house.rooms, house.price)
        house.rooms > 10 -> Palace(house.rooms, house.price)
        else -> Palace(house.rooms, house.price)
    }
    val basePrice = when {
        currentHouse.price < 0 -> 0.0
        currentHouse.price > 1_000_000 -> 1_000_000.0
        else -> currentHouse.price.toDouble()
    }
    val finalPrice = basePrice * currentHouse.coeficcient
    return if (finalPrice % 1.0 == 0.0) finalPrice.roundToInt() else finalPrice
}

open class House(val rooms: Int, val price: Int)
open class SubTypeHouse(rooms: Int, price: Int, val coeficcient: Double) : House(rooms, price)
class Cabin(rooms: Int, price: Int) : SubTypeHouse(rooms, price, 1.0)
class Bungalow(rooms: Int, price: Int) : SubTypeHouse(rooms, price, 1.2)
class Cottage(rooms: Int, price: Int) : SubTypeHouse(rooms, price, 1.25)
class Mansion(rooms: Int, price: Int) : SubTypeHouse(rooms, price, 1.4)
class Palace(rooms: Int, price: Int) : SubTypeHouse(rooms, price, 1.6)