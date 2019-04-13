package com.hogp.testingeverything.hotcoldobservables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hogp.testingeverything.R
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_hot_cold_observables.*
import java.util.*

class HotColdObservablesActivity : AppCompatActivity() {
    var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var observable: Observable<String> = testCold()
        setContentView(R.layout.activity_hot_cold_observables)
        button.setOnClickListener {
            counter++
            observable = testCold()
        }
        button2.setOnClickListener {
            observable.subscribe {
                println("TTTTTTTTTTTTTTTTTTTTT")
                println(it)
            }
        }


    }

    fun testCold(): Observable<String> {
        return Observable.just("Ahmed", "Zain", (counter).toString())
                .subscribeOn(Schedulers.io())
    }
}
