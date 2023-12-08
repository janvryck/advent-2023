package be.tabs_spaces.advent2023.days

import be.tabs_spaces.advent2023.util.lowestCommonMultiple

class Day08 : Day(8) {

    override fun partOne() = aaaToZZZ(inputList)

    override fun partTwo() = xxaToXXZ(inputList)

    fun aaaToZZZ(input: List<String>): Int {
        val instructions = input.first().map { it.toInstruction() }
        val network = input.drop(2).associate {
            it.extractNode() to (it.extractLeft() to it.extractRight())
        }

        var currentNode = network.entries.find { it.key == "AAA" }!!
        var steps = 0

        while (currentNode.key != "ZZZ") {
            val instruction = instructions[steps % instructions.size]
            with(instruction) {
                val target = currentNode.value.target()
                currentNode = network.entries.find { it.key == target }!!
            }
            steps++
        }

        return steps
    }

    fun xxaToXXZ(input: List<String>): Long {
        val instructions = input.first().map { it.toInstruction() }
        val network = input.drop(2).associate {
            it.extractNode() to (it.extractLeft() to it.extractRight())
        }

        return network.entries
            .filter { it.key.endsWith('A') }
            .map { it.toPair() }
            .map {
                var currentNode = it
                var steps = 0L

                while (!currentNode.first.endsWith("Z")) {
                    val instruction = instructions[(steps % instructions.size).toInt()]
                    with(instruction) {
                        val target = currentNode.second.target()
                        currentNode = target to network[target]!!
                    }
                    steps++
                }
                return@map steps
            }.reduce(::lowestCommonMultiple)
    }

    private fun String.extractNode() = substringBefore(" = ")
    private fun String.extractLeft() = substringAfter("(").substringBefore(",")
    private fun String.extractRight() = substringAfter(", ").substringBefore(")")
    private fun Char.toInstruction() = if (this == 'R') Instruction.R else Instruction.L

    enum class Instruction {
        R,
        L,
        ;

        fun Pair<String, String>.target() = when (this@Instruction) {
            R -> this.second
            L -> this.first
        }
    }
}
