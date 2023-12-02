package be.tabs_spaces.advent2023.days

import kotlin.reflect.KProperty1

class Day02 : Day(2) {

    private val configuration = GameConfiguration(
        red = 12,
        green = 13,
        blue = 14
    )
    private val games = inputList
        .map { Game.parse(it) }

    override fun partOne() = games
        .filter { it.rounds.all { round -> round.matches(configuration) } }
        .sumOf { it.id }

    override fun partTwo() = games.sumOf { it.power() }
}

data class GameConfiguration(
    val red: Int,
    val green: Int,
    val blue: Int,
)

data class Game(
    val id: Int,
    val rounds: List<GameRound>
) {
    companion object {
        fun parse(line: String) = Game(
            id = line.substringAfter("Game ").substringBefore(":").toInt(),
            rounds = line.extractRounds()
                .map {
                    GameRound(
                        red = it.extractValueFor("red"),
                        green = it.extractValueFor("green"),
                        blue = it.extractValueFor("blue")
                    )
                })

        private fun String.extractRounds() = substringAfter(":")
            .split(";")
            .map { it.split(", ") }

        private fun List<String>.extractValueFor(
            color: String
        ) = filter { it.contains(color) }
            .map { it.trim().substringBefore(" ") }
            .map { it.toInt() }
            .firstOrNull() ?: 0
    }

    fun power() = rounds.maxOf(GameRound::red) * rounds.maxOf(GameRound::green) * rounds.maxOf(GameRound::blue)

    private fun List<GameRound>.maxOf(property: KProperty1<GameRound, Int>) = map(property).filter { it > 0 }.max()
}

data class GameRound(
    val red: Int,
    val green: Int,
    val blue: Int,
) {
    fun matches(configuration: GameConfiguration) = red <= configuration.red && green <= configuration.green && blue <= configuration.blue

}
