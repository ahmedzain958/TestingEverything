package com.hogp.testingeverything.recyclerviewfilters.edmtv

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCLient {
    private var instance: Retrofit? = null
    val getInstance: Retrofit
        get() {
            if (instance == null)
                instance = Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            return instance!!
        }
}