package com.hogp.testingeverything.kotlin

fun main() {
    val m = Meeting1()
    m.addTitle("")
    val title: String? = m.meetingTitle()

    println(title)
}