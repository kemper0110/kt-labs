package org.example.utils

class IdSequence(startId: Int = 0) {
    private var id = startId
    fun next(): Int {
        return id++
    }
}