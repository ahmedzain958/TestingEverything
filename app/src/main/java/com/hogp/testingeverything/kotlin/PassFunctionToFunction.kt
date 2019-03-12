package com.hogp.testingeverything.kotlin


class Person(var name: String) {
    fun displayWithLamda(func: (s: String) -> Unit) {
        func(name)
    }
}

fun main() {
    val zain = Person("zain")
    println(zain.displayWithLamda (::testFunc))
}

fun testFunc(name: String) {
    println(name)
}