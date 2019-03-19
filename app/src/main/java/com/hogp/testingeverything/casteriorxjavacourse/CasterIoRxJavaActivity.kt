package com.hogp.testingeverything.casteriorxjavacourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hogp.testingeverything.R
import io.reactivex.Observable

class CasterIoRxJavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caster_io_rx_java)


    }

    /*fun getGistObservable(): Observable<Gist>{
        return Observable.just(getGist() )
    }*/
}
