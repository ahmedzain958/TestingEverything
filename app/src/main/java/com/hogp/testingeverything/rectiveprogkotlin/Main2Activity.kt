package com.hogp.testingeverything.rectiveprogkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hogp.testingeverything.R
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.concurrent.TimeUnit

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var emitter = PublishSubject.create<View>()
        btn.setOnClickListener { it ->
            emitter.onNext(it)
        }
//ex 4
        emitter.map {
            incrementCounter()
        }.throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe{incrementCounter2()}
        /* ex 3
         val observer = object : Observer<View> {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: View) {
                incrementCounter2()
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
        emitter.map(object : Function<View, View> {
            override fun apply(t: View): View {
                incrementCounter()
                return t
            }
        }).throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(observer)
        */
        //using with publish subject which returns emmitter type of  publish subject which plays the role of Observable here

        /* ex 2
        emitter
                 .map(object : Function<View, View> {
                     override fun apply(t: View): View {
                         incrementCounter()
                         return t
                     }

                 })
                 .throttleFirst(1000, TimeUnit.MILLISECONDS)
                 .subscribe {
                     incrementCounter2()
                 }*/
        //the previous mewthod simplified by lamda
        /*ex 1
         emitter
                 .map { t ->
                     incrementCounter()
                     print(t)
                     t
                 }
                 .throttleFirst(1000, TimeUnit.MILLISECONDS)
                 .subscribe {
                     incrementCounter2()
                 }*/
        //using with rxview.click which returns any from the Observable
        /*ex 1
        RxView.clicks(btn)
                .map { it ->
                    //it type is any
                    print(it)
                    incrementCounter()
                }
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    incrementCounter2()
                }*/
    }

    private fun incrementCounter() {
        var counter: Int = (counter1.text.toString().toInt())
        counter++
        counter1.text = counter.toString()
    }

    private fun incrementCounter2() {
        var counter: Int = (counter2.text.toString().toInt())
        counter++
        counter2.text = counter.toString()
    }
}
