package com.example.drumTutor

import android.app.Activity
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_metronome.*
import java.util.*
import kotlin.concurrent.timerTask

class Metronome : Activity() {
    private var metronomeOn = false
    private var metronome = Timer("metronome", true)
    //  Initial BPM
    private var bpmVal: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metronome)
        croller.label = croller.progress.toString()
        croller.setOnProgressChangedListener {
            croller.label = croller.progress.toString()
            if (this.metronomeOn && bpmVal != croller.progress.toLong()) {
                stopMetronome()
                startMetronome()
            }
            bpmVal = croller.progress.toLong()

        }
    }

    fun toggleMetronome(view: View) {
        this.metronomeOn = !metronomeOn
        var count = 0
        if (this.metronomeOn) {
            startMetronome()
        } else {
            stopMetronome()
        }
    }

    private fun startMetronome() {
        this.metronome.schedule(
            timerTask {
                val tone1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                tone1.startTone(ToneGenerator.TONE_PROP_BEEP)
                tone1.release()
            }, 500L, calculatePause()
        )
    }

    private fun stopMetronome() {
        this.metronome.cancel()
        newTimer()
    }

    private fun newTimer() {
        this.metronome = Timer("metronome", true)
    }

    private fun calculatePause(): Long {
        return (1000.0 * (60.0 / this.bpmVal)).toLong()
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

    override fun onPause() {
        this.metronome.cancel()
        super.onPause()
    }

    override fun onStop() {
        this.metronome.cancel()
        super.onStop()
    }


}
