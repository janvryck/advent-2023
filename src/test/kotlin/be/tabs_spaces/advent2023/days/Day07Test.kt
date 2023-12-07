package be.tabs_spaces.advent2073.days

import be.tabs_spaces.advent2023.days.Day07
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day07Test {
    private val day07 = Day07()

    @Test
    fun partOne() {
        assertThat(day07.partOne()).isEqualTo(6440)
    }

    @Test
    fun partTwo() {
        assertThat(day07.partTwo()).isEqualTo(5905)
    }

}
