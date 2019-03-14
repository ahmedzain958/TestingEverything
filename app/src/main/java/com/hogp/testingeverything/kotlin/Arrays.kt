package com.hogp.testingeverything.kotlin

fun main(args: Array<String>) {
    for (i in args.indices)
        println("$i ${args[i]}")

    val items = IntArray(2)
println("------------")
    val numbers = intArrayOf(1, 2, 3, 4, 5)

    numbers.forEachIndexed { index, i -> println("$index - $i") }
}