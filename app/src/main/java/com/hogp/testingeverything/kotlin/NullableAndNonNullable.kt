package com.hogp.testingeverything.kotlin


fun main() {
    val nullableMeting: Meeting? = null
    val nonnullableMeting: Meeting = Meeting()

    closeMeeting(nonnullableMeting)
    closeMeeting(nullableMeting)
    val o: ISaveble = ISaveble()
    val savable = o as? ISaveble
    println(savable?.save())
}

fun closeMeeting(m: Meeting?): Boolean? {
    return if (m?.canClose==true) m.close() else false
}

class ISaveble {
    fun save() {

    }
}

 open class Meeting {
    val canClose: Boolean = false
    fun close(): Boolean {
        return true
    }
}