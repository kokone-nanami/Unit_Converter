package converter

val conversion: List<Double> = listOf<Double>(1.0, 1000.0, 0.01, 0.001, 1609.35, 0.9144, 0.3048, 0.0254)
val units = listOf(
        listOf("meter", "meters"), listOf("kilometer", "kilometers"), listOf("centimeter", "centimeters"),
        listOf("millimeter", "millimeters"), listOf("mile", "miles"), listOf("yard", "yards"),
        listOf("foot", "feet"), listOf("inch", "inches")
)

fun main() {
    println("Enter a number and a measure of length:")
    val line = readLine()
    val s = line!!.split(" ")
    val conv = when (s[1].toLowerCase()) {
        "m", "meter", "meters" -> 0
        "km", "kilometer", "kilometers" -> 1
        "cm", "centimeter", "centimeters" -> 2
        "mm", "millimeter", "millimeters" -> 3
        "mi", "mile", "miles" -> 4
        "yd", "yard", "yards" -> 5
        "ft", "foot", "feet" -> 6
        "in", "inch", "inches" -> 7
        else -> throw Exception("wrong input!")
    }
    val y = s[0].toDouble()
    val x = y * conversion[conv]
    println("$y " + (if (y == 1.0) units[conv][0] else units[conv][1]) + " is $x " + if (x == 1.0) "meter" else "meters")
}
