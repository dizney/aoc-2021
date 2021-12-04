typealias Numbers = List<Int>
typealias BingoCard = List<Numbers>

fun main() {

    fun mapRows(input: List<String>): List<Numbers> = input
        .map { it.trim().split("\\s+".toRegex()).map { strVal -> strVal.toInt() } }

    fun mapToColumns(rows: List<Numbers>): List<Numbers> {
        return (0 until 5).map { columnNumber ->
            (0 until 5).flatMap { rowNumber ->
                listOf(rows[rowNumber][columnNumber])
            }
        }
    }

    fun parseData(input: List<String>): Pair<List<Int>, List<BingoCard>> {
        val drawnNumbers = input[0].split(",").map { it.toInt() }

        val bingoCards = input
            .filter { it.isNotBlank() }
            .drop(1)
            .chunked(5) { mapRows(it) }
        return Pair(drawnNumbers, bingoCards)
    }

    fun hasBingo(card: BingoCard, numbers: List<Int>): Boolean {
        val horizMatch = card.any { it.intersect(numbers.toSet()).size == 5 }
        if (!horizMatch) {
            return mapToColumns(card).any { it.intersect(numbers.toSet()).size == 5 }
        }
        return true
    }

    fun part1(input: List<String>): Int {
        val (drawnNumbers, bingoCards) = parseData(input)
        println(bingoCards.size)

        var numbersCount = 5
        var hasBingoIndex = -1
        do {
            bingoCards.forEachIndexed { index, it ->
                if (hasBingo(it, drawnNumbers.take(numbersCount))) {
                    hasBingoIndex = index
                }
            }
            numbersCount++
        } while (hasBingoIndex == -1)
        println(hasBingoIndex)
        println(bingoCards[hasBingoIndex])
        val numbersToWin = drawnNumbers.take(numbersCount - 1)
        println(numbersToWin)

        return bingoCards[hasBingoIndex].flatMap {
            it.subtract(numbersToWin.toSet())
        }
            .sum()
            .times(numbersToWin.last())
    }

    fun part2(input: List<String>): Int {
        println("*** PART 2 ***")
        val (drawnNumbers, bingoCards) = parseData(input)
        println(bingoCards.size)

        var numbersCount = 5
        val hasBingoIndices = mutableSetOf<Pair<Int, Int>>()
        do {
            bingoCards.forEachIndexed { index, it ->
                if (hasBingo(it, drawnNumbers.take(numbersCount))) {
                    if (hasBingoIndices.none { it.first == index }) {
                        hasBingoIndices.add(Pair(index, numbersCount))
                    }
                }
            }
            numbersCount++
        } while (numbersCount <= drawnNumbers.size)
        println(hasBingoIndices)
        val lastBingoCard = bingoCards[hasBingoIndices.last().first]
        println(lastBingoCard)
        val numbersToWin = drawnNumbers.take(hasBingoIndices.last().second)
        println(numbersToWin)
        println(numbersToWin.size)

        return lastBingoCard.flatMap {
            it.subtract(numbersToWin.toSet())
        }
            .sum()
            .times(numbersToWin.last())
    }

    val testInput = readInput("Day04_test")

    val part1Result = part1(testInput)
    println(part1Result)

    val part2Result = part2(testInput)
    println(part2Result)
}
