package be.tabs_spaces.advent2023.days

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day04Test {
    private val day04 = Day04()

    @Test
    fun partOne() {
        Assertions.assertThat(day04.partOne()).isEqualTo(13)
    }

    @Test
    fun partTwo() {
        Assertions.assertThat(day04.partTwo()).isEqualTo(30)
    }

}
