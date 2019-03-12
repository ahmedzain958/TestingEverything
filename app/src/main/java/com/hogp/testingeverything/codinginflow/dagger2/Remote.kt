package com.hogp.testingeverything.codinginflow.dagger2

import android.util.Log
import javax.inject.Inject


class Remote @Inject constructor() {
    private val TAG = "Car"
    fun setListener(car: Car) {
        Log.d(TAG, "Remote connected")
    }
}