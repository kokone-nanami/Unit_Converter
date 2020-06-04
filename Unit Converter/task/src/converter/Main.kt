package converter

import converter.Types.*
import converter.Measures.*

enum class Types {
    WEIGHT, LENGTH, TEMPERATURE
}

enum class Measures(val factor: Double?, val names: List<String>, val type: Types?) {
    GRAM(1.0, listOf("g", "gram", "grams"), WEIGHT),
    KILOGRAM(1000.0, listOf("kg", "kilogram", "kilograms"), WEIGHT),
    MILLIGRAM(0.001, listOf("mg", "milligram", "milligrams"), WEIGHT),
    POUNDS(453.592, listOf("lb", "pound", "pounds"), WEIGHT),
    OUNCE(28.3495, listOf("oz", "ounce", "ounces"), WEIGHT),

    METER(1.0, listOf("m", "meter", "meters"), LENGTH),
    KILOMETER(1000.0, listOf("km", "kilometer", "kilometers"), LENGTH),
    CENTIMETER(0.01, listOf("cm", "centimeter", "centimeters"), LENGTH),
    MILLIMETER(0.001, listOf("mm", "millimeter", "millimeters"), LENGTH),
    MILE(1609.35, listOf("mi", "mile", "miles"), LENGTH),
    YARD(0.9144, listOf("yd", "yard", "yards"), LENGTH),
    FOOT(0.3048, listOf("ft", "foot", "feet"), LENGTH),
    INCH(0.0254, listOf("in", "inch", "inches"), LENGTH),

    CELSIUS(null, listOf("c", "degree Celsius", "degrees Celsius", "celsius", "dc", "degree celsius", "degrees celsius"), TEMPERATURE),
    FAHRENHEIT(null, listOf("f", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "degree fahrenheit", "degrees fahrenheit"), TEMPERATURE),
    KELVINS(null, listOf("k", "Kelvin", "Kelvins", "kelvin", "kelvins"), TEMPERATURE),

    UNKNOWN(null, listOf("???", "???", "???"), null);

    companion object {
        fun getMeasures(measure: String): Measures {
            for (x in values())
                if (x.names.contains(measure))
                    return x
            return UNKNOWN
        }

        fun convertToBase(measure: Measures, value: Double): Double? = when (measure) {
            GRAM, KILOGRAM, MILLIGRAM, POUNDS, OUNCE, METER, KILOMETER, CENTIMETER, MILLIMETER, MILE, YARD, FOOT, INCH
                -> measure.factor!! * value
            CELSIUS -> value
            FAHRENHEIT -> (value - 32) * 5/9
            KELVINS -> value - 273.15
            UNKNOWN -> null
        }

        fun convertOffBase(measure: Measures, value: Double): Double? = when (measure) {
            GRAM, KILOGRAM, MILLIGRAM, POUNDS, OUNCE, METER, KILOMETER, CENTIMETER, MILLIMETER, MILE, YARD, FOOT, INCH
            -> value / measure.factor!!
            CELSIUS -> value
            FAHRENHEIT -> value * 9/5 + 32
            KELVINS -> value + 273.15
            UNKNOWN -> null
        }
    }
}

fun main() {
    println("Enter what you want to convert (or exit):")
    var input = readLine()
    while (input != "exit") {
        val lines: Array<String> = input!!.split(" ").toTypedArray()
        var doubleLength = false
        if (lines[1].toLowerCase() == "degree" || lines[1].toLowerCase() == "degrees") {
            lines[1] = lines[1] + " " + lines[2]
            doubleLength = true
        }

        if (doubleLength) {
            if (lines[4].toLowerCase() == "degree" || lines[4].toLowerCase() == "degrees")
                lines[4] = lines[4] + " " + lines[5]
        } else {
            if (lines[3].toLowerCase() == "degree" || lines[3].toLowerCase() == "degrees")
                lines[3] = lines[3] + " " + lines[4]
        }

        try {
            val firstPos = 1; val lastPos = if (doubleLength) 4 else 3
            val start = Measures.getMeasures(lines[firstPos].toLowerCase())
            val end = Measures.getMeasures(lines[lastPos].toLowerCase())
            val initial = lines[0].toDouble()
            if (start.type == end.type && start.type != null && end.type != null) {
                if (start.type == WEIGHT && initial < 0)
                    println("Weight shouldn't be negative.")
                else if (start.type == LENGTH && initial < 0)
                    println("Length shouldn't be negative.")
                else {
                    val converted = Measures.convertOffBase(end, Measures.convertToBase(start, initial)!!)
                    val startWord = if (initial == 1.0) start.names[1] else start.names[2]
                    val endWord = if (converted == 1.0) end.names[1] else end.names[2]

                    println("$initial $startWord is $converted $endWord")
                }
            } else {
                println("Conversion from ${start.names[2]} to ${end.names[2]} is impossible")
            }
        } catch (e: Exception) {
            println("Parse error")
        }
        println("Enter what you want to convert (or exit):")
        input = readLine()
    }
}