package com.hogp.testingeverything

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    data class PersonForm(
            val firstName: String,
            val lastName: String,
            val age: Int,
            // maybe many fields exist here like address, card number, etc.
            val tel: String
    )
    // maps to ...
    data class PersonRecord(
            val name: String, // "${firstName} ${lastName}"
            val age: Int, // copy of age
            // maybe many fields exist here like address, card number, etc.
            val tel: String // copy of tel
    )


}