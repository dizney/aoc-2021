fun main() {
    fun part1(input: List<String>): Int {
        return input.fold(Pair(0, 0)) { acc, movement ->
            val (x, y) = acc
            val (direction, amountString) = movement.split(" ")
            val amount = amountString.toInt()
            when (direction) {
                "forward" -> Pair(x + amount, y)
                "down" -> Pair(x, y + amount)
                "up" -> Pair(x, y - amount)
                else -> throw IllegalStateException("Unknown movement direction $direction")
            }
        }
            .let { (x, y) -> x * y }
    }

    fun part2(input: List<String>): Int {
        return input.fold(Triple(0, 0, 0)) { acc, movement ->
            val (x, y, aim) = acc
            val (direction, amountString) = movement.split(" ")
            val amount = amountString.toInt()
            when (direction) {
                "forward" -> Triple(x + amount, y + aim * amount, aim)
                "down" -> Triple(x, y, aim + amount)
                "up" -> Triple(x, y, aim - amount)
                else -> throw IllegalStateException("Unknown movement direction $direction")
            }
        }
            .let { (x, y) -> x * y }
    }

    val testInput = readInput("Day02_test")

    val part1Result = part1(testInput)
    println(part1Result)

    val part2Result = part2(testInput)
    println(part2Result)
}
