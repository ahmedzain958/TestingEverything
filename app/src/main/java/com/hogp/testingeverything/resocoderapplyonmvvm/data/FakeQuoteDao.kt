package com.hogp.testingeverything.resocoderapplyonmvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuoteDao {
    private val quoteList = mutableListOf<Quote>()
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quoteList
    }

    fun addQuote(quote: Quote) {
        quoteList.add(quote)
        quotes.value = quoteList
    }


    object singleExample {
        private val x = 2;
        val y = 4;
        fun multiply(): Int {
            return x * y
        }
    }

    fun getQuotes() = quotes as LiveData<List<Quote>>
}
class A{

fun  multiply(){

}
}
fun main() {
    println(FakeQuoteDao.singleExample.y)
    println(FakeQuoteDao.singleExample.multiply())
}