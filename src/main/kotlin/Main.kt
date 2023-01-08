private fun calculateFinalTemperature(initMeasurement: Double, initialUnit: String, finalUnit: String): Double {
    return if (startingCharacter(initialUnit, "c") && startingCharacter(finalUnit, "k"))
        initMeasurement + 273.15
    else if (startingCharacter(initialUnit, "k") && startingCharacter(finalUnit, "c"))
        initMeasurement - 273.15
    else if (startingCharacter(initialUnit, "f") && startingCharacter(finalUnit, "c"))
        (initMeasurement - 32.0) * 5.0 / 9.0
    else if (startingCharacter(initialUnit, "c") && startingCharacter(finalUnit, "f"))
        (9.0 / 5.0 * initMeasurement) + 32.0
    else if (startingCharacter(initialUnit, "f") && startingCharacter(finalUnit, "k"))
        5.0 / 9.0 * (initMeasurement - 32.0) + 273.15
    else
        ((initMeasurement - 273.15) * 9.0 / 5.0) + 32.0
}

private fun startingCharacter(initialUnit: String, prefix: String) = initialUnit.lowercase().startsWith(prefix)

fun main() {
    printFinalTemperature(27.0, "CelsiUs", "FahRenhEit") { initMeasurement, initialUnit, finalUnit ->
        calculateFinalTemperature(initMeasurement, initialUnit, finalUnit)
    }
    printFinalTemperature(350.0, "kelviN", "CelsIus") { initMeasurement, initialUnit, finalUnit ->
        calculateFinalTemperature(initMeasurement, initialUnit, finalUnit)
    }
    printFinalTemperature(10.0, "faHreNHeiT", "KeLVin") { initMeasurement, initialUnit, finalUnit ->
        calculateFinalTemperature(initMeasurement, initialUnit, finalUnit)
    }
}

private fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double, String, String) -> Double
) {
    val finalMeasurement =
        String.format("%.2f", conversionFormula(initialMeasurement, initialUnit, finalUnit)) // two decimal places
    println(
        "$initialMeasurement degrees ${capitalizeWord(initialUnit)} is $finalMeasurement degrees ${
            capitalizeWord(
                finalUnit
            )
        }."
    )
}

private fun capitalizeWord(word: String) = if (word.isNotEmpty())
    word.lowercase().replaceFirst(word.lowercase().first(), word.uppercase().first())
else
    word
