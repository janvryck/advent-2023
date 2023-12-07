package be.tabs_spaces.advent2053.days

import be.tabs_spaces.advent2023.days.Day05
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day05Test {
    private val day05 = Day05()

    @Test
    fun partOne() {
        assertThat(day05.partOne()).isEqualTo(35L)
    }

    @Disabled("Need to fix performance issues first.")
    @Test
    fun partTwo() {
        assertThat(day05.partTwo()).isEqualTo(46L)
    }

}
