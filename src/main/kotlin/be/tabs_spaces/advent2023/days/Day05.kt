package be.tabs_spaces.advent2023.days

class Day05 : Day(5) {

    override fun partOne() = Almanac(inputString).lowestSeedLocation()

    override fun partTwo() = Any()
        //  Runs for 3+ min, setting this in comment: Almanac(inputString, seedRanges = true).lowestSeedLocation()

    class Almanac(
        rawAlmanac: String,
        seedRanges: Boolean = false
    ) {

        private val seeds: List<Pair<Long, Long>> = rawAlmanac.substringAfter("seeds: ")
            .substringBefore("\n")
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.toLong() }
            .let { seeds ->
                if (seedRanges) {
                    seeds.windowed(size = 2, step = 2).map { it.first() to it.last() }
                } else seeds.map { it to 1 }
            }

        private val seedToSoil = rawAlmanac.toMappingsFor("seed-to-soil")
        private val soilToFertilizer = rawAlmanac.toMappingsFor("soil-to-fertilizer")
        private val fertilizerToWater = rawAlmanac.toMappingsFor("fertilizer-to-water")
        private val waterToLight = rawAlmanac.toMappingsFor("water-to-light")
        private val lightToTemp = rawAlmanac.toMappingsFor("light-to-temperature")
        private val tempToHumidity = rawAlmanac.toMappingsFor("temperature-to-humidity")
        private val humidityToLocation = rawAlmanac.toMappingsFor("humidity-to-location")

        fun lowestSeedLocation() = seeds.asSequence()
            .flatMap { (it.first until it.first + it.second).asSequence() }
            .minOf { locationFor(it) }

        private fun locationFor(seed: Long) = seedToSoil.destinationFor(seed)//.also { print("Seed $seed, soil $it, ") }
            .let { soilToFertilizer.destinationFor(it) }//.also { print("fertilizer $it, ") }
            .let { fertilizerToWater.destinationFor(it) }//.also { print("water $it, ") }
            .let { waterToLight.destinationFor(it) }//.also { print("light $it, ") }
            .let { lightToTemp.destinationFor(it) }//.also { print("temp $it, ") }
            .let { tempToHumidity.destinationFor(it) }//.also { print("humidity $it, ") }
            .let { humidityToLocation.destinationFor(it) }//.also { println("location $it") }
    }

    data class DestinationToSourceMappings(
        val mappings: List<DestinationToSourceMapping>
    ) {
        fun destinationFor(source: Long) = mappings
            .find { it.sourceRange.contains(source) }
            ?.destinationFor(source)
            ?: source
    }

    data class DestinationToSourceMapping(
        val destinationStart: Long,
        val sourceStart: Long,
        val rangeLength: Long,
    ) {

        val sourceRange
            get() = sourceStart until (sourceStart + rangeLength)

        fun destinationFor(source: Long) = source + (destinationStart - sourceStart)

        companion object {
            fun parse(rawMapping: String) = rawMapping
                .split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toLong() }
                .let {
                    DestinationToSourceMapping(it[0], it[1], it[2])
                }
        }
    }

}

private fun String.toMappingsFor(type: String) = substringAfter("$type map:\n")
    .substringBefore("\n\n")
    .lines()
    .filter { it.isNotEmpty() }
    .map { Day05.DestinationToSourceMapping.parse(it) }
    .let { Day05.DestinationToSourceMappings(it) }
