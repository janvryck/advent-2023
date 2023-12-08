package be.tabs_spaces.advent2023.util

fun greatestCommonDivisor(a: Int, b: Int): Int = when (b) {
    0 -> a
    else -> greatestCommonDivisor(b, a % b)
}

fun lowestCommonMultiple(a: Int, b: Int): Int =
    (a * b) / greatestCommonDivisor(a, b)

fun greatestCommonDivisor(a: Long, b: Long): Long = when (b) {
    0L -> a
    else -> greatestCommonDivisor(b, a % b)
}

fun lowestCommonMultiple(a: Long, b: Long): Long =
    (a * b) / greatestCommonDivisor(a, b)
