package be.tabs_spaces.advent2063.days

import be.tabs_spaces.advent2023.days.Day06
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day06Test {
    private val day06 = Day06()

    @Test
    fun partOne() {
        assertThat(day06.partOne()).isEqualTo(288)
    }

    @Test
    fun partTwo() {
        assertThat(day06.partTwo()).isEqualTo(71503)
    }

}
