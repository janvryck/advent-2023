package be.tabs_spaces.advent2023.days

import be.tabs_spaces.advent2023.days.Day03.EngineNumber.Companion.INITIAL
import be.tabs_spaces.advent2023.days.Day03.Gear.Companion.GEAR
import be.tabs_spaces.advent2023.util.Point

class Day03 : Day(3) {

    private val engineSchematic = EngineSchematic.parse(inputList)

    override fun partOne() = engineSchematic.partNumbers().sumOf { it.value }

    override fun partTwo(): Any {
        return engineSchematic.gears().sumOf { it.gearRatio() }
    }

    private data class EngineSchematic(
        val engineNumbers: List<EngineNumber>,
        val symbols: List<Symbol>
    ) {

        companion object {
            fun parse(rawSchematic: List<String>): EngineSchematic {
                val numbers = mutableListOf<EngineNumber>()
                val symbols = mutableListOf<Symbol>()

                var currentNumber = INITIAL
                rawSchematic.forEachIndexed { y, row ->
                    row.forEachIndexed { x, char ->
                        if (char.isDigit()) {
                            currentNumber += EngineNumber(char.digitToInt(), listOf(Point(x, y)))
                        } else {
                            if (char != '.') {
                                symbols += Symbol(char, Point(x, y))
                            }
                            if (currentNumber.isNotInitial()) {
                                numbers += currentNumber
                                currentNumber = INITIAL
                            }
                        }
                    }

                    if (currentNumber.isNotInitial()) {
                        numbers += currentNumber
                        currentNumber = INITIAL
                    }
                }

                return EngineSchematic(numbers, symbols)
            }
        }

        fun partNumbers() = engineNumbers.filter { it.isPartNumber(symbols) }

        fun gears() = symbols
            .filter { it.symbol == GEAR }
            .mapNotNull { symbol ->
                partNumbers()
                    .filter { partNumber -> partNumber.neighbours(symbol) }
                    .takeIf { it.size == 2 }
                    ?.let { partNumbers -> Gear(symbol, partNumbers) }
            }
    }

    private data class EngineNumber(
        val value: Int,
        val location: List<Point>
    ) {

        companion object {
            val INITIAL = EngineNumber(0, emptyList())
        }

        operator fun plus(engineNumber: EngineNumber) = EngineNumber(
            value * 10 + engineNumber.value,
            location + engineNumber.location
        )

        fun isNotInitial() = !(value == 0 && location.isEmpty())

        fun isPartNumber(symbols: List<Symbol>) = symbols
            .any { this.neighbours(it) }

        fun neighbours(symbol: Symbol) = location.any { it.neighbours(symbol.location) }
    }

    private data class Symbol(
        val symbol: Char,
        val location: Point
    )

    private data class Gear(
        val symbol: Symbol,
        val partNumbers: List<EngineNumber>
    ) {

        companion object {
            val GEAR = '*'
        }

        fun gearRatio() = partNumbers[0].value * partNumbers[1].value
    }
}
