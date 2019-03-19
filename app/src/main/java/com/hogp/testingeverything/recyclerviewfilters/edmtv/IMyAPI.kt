package com.hogp.testingeverything.recyclerviewfilters.edmtv

import com.hogp.testingeverything.recyclerviewfilters.edmtv.model.Item
import io.reactivex.Observable
import retrofit2.http.GET

interface IMyAPI {
    @get:GET("photos")
    val photos: Observable<List<Item>>
}