fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.windowed(2).fold(0) { acc, entry ->
            acc + if (entry.first() < entry[1]) 1 else 0
        }
    }

    fun part2(input: List<String>): Int {
        return input.asSequence()
            .map { it.toInt() }
            .windowed(3).map { it.sum() }
            .windowed(2).fold(0) { acc, entry ->
                acc + if (entry.first() < entry[1]) 1 else 0
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")

    val part1Result = part1(testInput)
    println(part1Result)

    val part2Result = part2(testInput)
    println(part2Result)
}
