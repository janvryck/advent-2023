package be.tabs_spaces.advent2023.days

import kotlin.math.pow

class Day04 : Day(4) {

    private val scratchcards: List<Scratchcard> = inputList.map { it.toScratchcard() }

    override fun partOne() = scratchcards.sumOf { it.points() }

    override fun partTwo() = scratchcards
        .associate { it.cardNumber to 1 }
        .toMutableMap()
        .apply {
            scratchcards.forEach { card ->
                card.copies().forEach {
                    this[it] = get(it)!! + get(card.cardNumber)!!
                }
            }
        }.values.sum()

    data class Scratchcard(
        val cardNumber: Int,
        val winning: List<Int>,
        val actual: List<Int>
    ) {

        private fun noOfMatches() = actual
            .filter { it in winning }
            .size

        fun points() = if (noOfMatches() > 0) 2.0.pow(noOfMatches() - 1.0).toInt() else 0

        fun copies() = ((cardNumber +1) ..  cardNumber + noOfMatches()).toList()
    }

    private fun String.toScratchcard(): Scratchcard = Scratchcard(
        cardNumber = substringBefore(": ").substringAfter("Card ").trim().toInt(),
        winning = substringAfter(": ").substringBefore(" | ").toNumbers(),
        actual = substringAfter(" | ").toNumbers(),
    )

    private fun String.toNumbers() = split(" ")
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
}
