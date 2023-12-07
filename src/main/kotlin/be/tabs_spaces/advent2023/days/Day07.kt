package be.tabs_spaces.advent2023.days

private const val ORDER = "23456789TJQKA"
private const val ORDER_WITH_JOKERS = "J23456789TQKA"
private const val JOKER = 'J'

class Day07 : Day(7) {

    override fun partOne() = CamelCards.parse(inputList).totalWinnings()

    override fun partTwo() = CamelCards.parse(inputList, withJokers = true).totalWinnings()

    data class CamelCards(
        val hands: List<Hand>,
        val withJokers: Boolean = false
    ) {

        companion object {
            fun parse(input: List<String>, withJokers: Boolean = false) = CamelCards(
                hands = input.map { Hand.parse(it, withJokers) }
            )
        }

        fun totalWinnings() = hands.sorted()
            .mapIndexed { index, hand -> (index + 1) * hand.bid }
            .sum()
    }

    data class Hand(
        val cards: String,
        val bid: Int,
        val withJokers: Boolean
    ) : Comparable<Hand> {

        private val signature: List<Int> = cards
            .countCardsByOccurrence()
            .includeJokersIntoHighestCount()
        private val type: Type = Type.determineType(signature)

        companion object {
            fun parse(rawHand: String, withJokers: Boolean) = rawHand.split(" ").let {
                Hand(it[0], it[1].toInt(), withJokers)
            }
        }

        override fun compareTo(other: Hand) = if (type == other.type) {
            (0..4).firstNotNullOf { idx ->
                if (cards[idx] != other.cards[idx])
                    cards[idx].value().compareTo(other.cards[idx].value())
                else null
            }
        } else type.compareTo(other.type)

        private fun Char.value() = if (withJokers) ORDER_WITH_JOKERS.indexOf(this) else ORDER.indexOf(this)

        private fun String.countCardsByOccurrence() = filter { if (withJokers) it != JOKER else true }
            .groupingBy { it }
            .eachCount()
            .values
            .sorted()

        private fun List<Int>.includeJokersIntoHighestCount() = toMutableList()
            .apply {
                if (withJokers && isNotEmpty()) {
                    this[size - 1] = last() + cards.count { it == JOKER }
                } else if (withJokers) this.add(5)
            }.toList()

    }

    enum class Type(val signature: List<Int>) {
        HIGH_CARD(       listOf(1, 1, 1, 1, 1)),
        ONE_PAIR(        listOf(1, 1, 1, 2)),
        TWO_PAIR(        listOf(1, 2, 2)),
        THREE_OF_A_KIND( listOf(1, 1, 3)),
        FULL_HOUSE(      listOf(2, 3)),
        FOUR_OF_A_KIND(  listOf(1, 4)),
        FIVE_OF_A_KIND(  listOf(5)),
        ;

        companion object {
            fun determineType(signature: List<Int>) = when (signature) {
                HIGH_CARD.signature -> HIGH_CARD
                ONE_PAIR.signature -> ONE_PAIR
                TWO_PAIR.signature -> TWO_PAIR
                THREE_OF_A_KIND.signature -> THREE_OF_A_KIND
                FULL_HOUSE.signature -> FULL_HOUSE
                FOUR_OF_A_KIND.signature -> FOUR_OF_A_KIND
                FIVE_OF_A_KIND.signature -> FIVE_OF_A_KIND
                else -> throw IllegalArgumentException("Signature does not match known types.")
            }
        }
    }
}
