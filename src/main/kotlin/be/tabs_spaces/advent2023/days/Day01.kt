package be.tabs_spaces.advent2023.days

class Day01 : Day(1) {

    private val digits = mapOf(
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
    )
    private val numericals = digits + mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    override fun partOne() = mapToNumbers(digits)

    override fun partTwo() = mapToNumbers(numericals)

    private fun mapToNumbers(numericalMapping: Map<String, Int>) = inputList
        .map { "${it.firstValueFrom(numericalMapping)}${it.lastValueFrom(numericalMapping)}" }
        .sumOf { it.toInt() }

    private fun String.lastValueFrom(numericalMapping: Map<String, Int>) =
        numericalMapping[findLastAnyOf(numericalMapping.keys)!!.second]

    private fun String.firstValueFrom(numericalMapping: Map<String, Int>) =
        numericalMapping[findAnyOf(numericalMapping.keys)!!.second]
}
