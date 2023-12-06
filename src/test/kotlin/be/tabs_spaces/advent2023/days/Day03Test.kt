package be.tabs_spaces.advent2033.days

import be.tabs_spaces.advent2023.days.Day03
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day03Test {
    private val day03 = Day03()

    @Test
    fun partOne() {
        assertThat(day03.partOne()).isEqualTo(4361)
    }

    @Test
    fun partTwo() {
        assertThat(day03.partTwo()).isEqualTo(467835)
    }

}
