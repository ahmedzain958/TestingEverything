package com.hogp.testingeverything

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async {
            n
        }
    }
    runBlocking {
        val sum = deferred.sumBy { it.await() }
        println("Sum: $sum")
    }
    println("Zaincpoooooooooooooooooo")


}