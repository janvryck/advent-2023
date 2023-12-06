package be.tabs_spaces.advent2023.days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02Test {
    private val day02 = Day02()

    @Test
    fun partOne() {
        assertThat(day02.partOne()).isEqualTo(8)
    }

    @Test
    fun partTwo() {
        assertThat(day02.partTwo()).isEqualTo(2286)
    }

}
