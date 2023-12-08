package be.tabs_spaces.advent2023.days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day08Test {
    private val day08 = Day08()

    @Test
    fun partOne() {
        val input = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent().lines()

        assertThat(day08.aaaToZZZ(input)).isEqualTo(2)
    }
    @Test
    fun partOne_moreSteps() {
        val input = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent().lines()

        assertThat(day08.aaaToZZZ(input)).isEqualTo(6)
    }

    @Test
    fun partTwo() {
        val input = """
            LR
            
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent().lines()

        assertThat(day08.xxaToXXZ(input)).isEqualTo(6)
    }

}
