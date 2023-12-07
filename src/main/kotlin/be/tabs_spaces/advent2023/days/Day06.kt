package be.tabs_spaces.advent2023.days

class Day06: Day(6) {

    private val races = parseRaces()

    override fun partOne() = races.map { it.winningStrategies() }.reduce { acc, i -> acc * i }

    override fun partTwo() = races.join().winningStrategies()

    data class Race(
        val duration: Long,
        val distanceToBeat: Long
    ) {
        fun winningStrategies() = (1 until duration)
            .asSequence()
            .map { it to it * (duration - it) }
            .filter { it.second > distanceToBeat }
            .count()
    }

    private fun parseRaces(): List<Race> {
        val durations = inputList[0].drop(10).split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
        val distance = inputList[1].drop(10).split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
        return durations.indices.map {
            Race(durations[it], distance[it])
        }
    }

    private fun List<Race>.join(): Race {
        return Race(
            duration = this.map { it.duration }.joinToString(separator = "").toLong(),
            distanceToBeat = this.map { it.distanceToBeat }.joinToString(separator = "").toLong(),
        )
    }
}
