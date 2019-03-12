package com.hogp.testingeverything.codinginflow.dagger2

import android.util.Log
import javax.inject.Inject



class Car @Inject constructor(private val engine: Engine,private val  wheels: Wheels){
    private val TAG = "Car"

    @Inject
    fun enableRemote(remote: Remote) {
        remote.setListener(this)
    }

    fun drive() {
        Log.d(TAG, "driving...")
    }
}