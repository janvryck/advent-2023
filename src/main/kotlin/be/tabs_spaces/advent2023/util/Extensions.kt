package be.tabs_spaces.advent2023.util

fun <E> Collection<E>.takeUntil(predicate: (E) -> Boolean): Collection<E> {
    var flag = true
    return takeWhile { i ->
        flag.also { flag = predicate(i) }
    }
}
