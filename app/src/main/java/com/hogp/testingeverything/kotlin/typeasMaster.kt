package com.hogp.testingeverything.kotlin


open class Master {
}

class detail {

}

fun main() {
    var detail: detail? = detail()
    val master: Master
    val sss = detail as?Master
    println(sss)
}


