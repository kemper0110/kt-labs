package org.example.lab3.utils

fun idSequence(prefix: String, startId: Int = 0): Sequence<String> {
    return generateSequence(startId) { it + 1 }
        .map { "$prefix-$it" }
}