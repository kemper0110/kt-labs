package org.example.utils

class IdSequence(private val prefix: String, startId: Int = 0) {
    private var id = startId
    fun next(): String {
        return "$prefix-${id++}"
    }
}