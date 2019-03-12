package com.hogp.testingeverything.kotlin

fun main() {
    var people: List<Person1?>?
    people = mutableListOf(Person1(55), Person1(55), null)
    for (person: Person1 in people.filterNotNull()) {
        println(person.age)
    }

}

class Person1(val age: Int) {

}