package converter

enum class Measures(val factor: Double, val names: List<String>) {
    GRAM(1.0, listOf("g", "gram", "grams")),
    KILOGRAM(1000.0, listOf("kg", "kilogram", "kilograms")),
    MILLIGRAM(0.001, listOf("mg", "milligram", "milligrams")),
    POUNDS(453.592, listOf("lb", "pound", "pounds")),
    OUNCE(28.3495, listOf("oz", "ounce", "ounces")),

    METER(1.0, listOf("m", "meter", "meters")),
    KILOMETER(1000.0, listOf("km", "kilometer", "kilometers")),
    CENTIMETER(0.01, listOf("cm", "centimeter", "centimeters")),
    MILLIMETER(0.001, listOf("mm", "millimeter", "millimeters")),
    MILE(1609.35, listOf("mi", "mile", "miles")),
    YARD(0.9144, listOf("yd", "yard", "yards")),
    FOOT(0.3048, listOf("ft", "foot", "feet")),
    INCH(0.0254, listOf("in", "inch", "inches"));

    companion object {
        fun getMeasures(measure: String): Measures {
            for (x in values())
                if (x.names.contains(measure))
                    return x
            throw Exception("Unknown conversion unit.")
        }
    }
}

fun main() {
    println("Enter what you want to convert (or exit):")
    var input = readLine()
    while (input != "exit") {
        val lines = input!!.split(" ")
        val start = Measures.getMeasures(lines[1].toLowerCase())
        val end = Measures.getMeasures(lines[3].toLowerCase())
        val converted = lines[0].toDouble() * start.factor / end.factor
        val startWord = if (lines[0].toDouble() == 1.0) start.names[1] else start.names[2]
        val endWord = if (converted == 1.0) end.names[1] else end.names[2]

        println("${lines[0].toDouble()} $startWord is $converted $endWord")
        println("Enter what you want to convert (or exit):")
        input = readLine()
    }
}