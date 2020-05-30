package converter

fun main() {
    println("Enter a number of kilometers:")
    val km = readLine()!!.toInt()
    println("$km kilometers is ${km * 1000} meters")
}
