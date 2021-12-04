fun main() {
    fun commonValue(list: List<String>, position: Int, mostCommon: Boolean): Char {
        return list.map { it[position] }
            .let { bitList -> if (bitList.count { it == '0' } > (bitList.size / 2)) '0' else '1' }
            .let { if (mostCommon) it else (if (it == '0') '1' else '0') }
    }

    fun part1(input: List<String>): Int {
        val gammaRateChars = (0 until input[0].length)
            .map {
                bitIndex -> commonValue(input, bitIndex, true)
            }
            .joinToString("")
        val epsilonRateChars = gammaRateChars.map { if (it == '0') '1' else '0' }.joinToString("")
        return gammaRateChars.toInt(2) * epsilonRateChars.toInt(2)
    }

    fun filterOnCommonValue(input: List<String>, bitPosition: Int, mostCommon: Boolean): List<String> {
        if (input.size == 1) return input
        val commonValue = commonValue(input, bitPosition, mostCommon)
        return filterOnCommonValue(input.filter { it[bitPosition] == commonValue }, bitPosition + 1, mostCommon)
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRatingEntries = filterOnCommonValue(input, 0, true)
        val co2ScrubberRatingEntries = filterOnCommonValue(input, 0, false)
        return oxygenGeneratorRatingEntries[0].toInt(2) * co2ScrubberRatingEntries[0].toInt(2)
    }

    val testInput = readInput("Day03_test")

    val part1Result = part1(testInput)
    println(part1Result)

    val part2Result = part2(testInput)
    println(part2Result)
}
