package com.hogp.testingeverything.progressba

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.hogp.testingeverything.R
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class ProgressBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.hogp.testingeverything.R.layout.activity_progress_bar)
        val progressBar = ProgressBar(this)
        progressBar.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val linearLayout = findViewById(com.hogp.testingeverything.R.id.viewProgress) as LinearLayout
        // Add ProgressBar to LinearLayout
        if (linearLayout != null) {
            linearLayout!!.addView(progressBar)
        }

        val btn = findViewById(R.id.button) as Button
        if (btn != null) {
            btn.setOnClickListener {
                val visibility = if (progressBar.visibility == View.GONE) View.VISIBLE else View.GONE
                progressBar.visibility = visibility

                val btnText = if (progressBar.visibility == View.GONE) "SHOW PROGRESSBAR" else "HIDE PROGRESSBAR"
                btn!!.setText(btnText)
            }
        }

    }
}

