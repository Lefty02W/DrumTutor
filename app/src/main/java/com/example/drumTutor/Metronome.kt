package com.example.drumTutor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_metronome.*

class Metronome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metronome)
        croller.label = croller.progress.toString()

        croller.setOnProgressChangedListener { croller.label = croller.progress.toString() }
    }

    fun onProgressChanged(view: View) {
        croller.label = croller.progress.toString()
    }

    fun decrement(view: View) {
        croller.progress = croller.progress - 1
        onProgressChanged(view)
    }

    fun increment(view: View) {
        croller.progress = croller.progress + 1
        onProgressChanged(view)
    }
}
