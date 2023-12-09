package be.tabs_spaces.advent2023.days

class Day09 : Day(9) {

    private val oasisReport = inputList
        .map { reportLine -> reportLine.split(" ").map { it.toInt() } }

    override fun partOne() = oasisReport.sumOf { reportLine -> extrapolate(reportLine) }

    override fun partTwo() = oasisReport.sumOf { reportLine -> extrapolate(reportLine, backwards = true) }

    private fun extrapolate(
        values: List<Int>,
        backwards: Boolean = false
    ): Int = values
        .takeIf { values.any { it != 0 } }
        ?.zipWithNext()
        ?.map { it.second - it.first }
        ?.let { extrapolate(it, backwards) }
        ?.let { diff ->
            if (backwards) (values.first() - diff) else (values.last() + diff)
        }
        ?: 0

}
