package com.example.drumTutor

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.round

class RudimentTrainer : Activity() {

    private lateinit var pattern: Array<String>
    var currentPatternIndex = 0
    var atempts = 0.0
    var playedNoteQueue = ""
    var expectedNote = ""
    var correct = 0.0
    var link = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rudiment_trainer)
        pattern = intent?.extras?.get("pattern") as Array<String>
        link = intent.extras!!.get("link") as String
        CoroutineScope(Default).launch {
            updateAccuracy()
        }
        nextNote()
        findViewById<TextView>(R.id.rudimentHeading).apply {
            text = intent.extras?.get("name").toString()
        }

        val leftStick: Button = findViewById(R.id.leftStickBtn)
        val rightStick: Button = findViewById(R.id.rightStickBtn)

        leftStick.setOnClickListener {
            playedNoteQueue += "L"
            if (playedNoteQueue.length >= expectedNote.length) {
                checkResult()
            }
        }

        rightStick.setOnClickListener {
            playedNoteQueue += "R"
            if (playedNoteQueue.length >= expectedNote.length) {
                checkResult()
            }
        }
    }


    private fun checkResult() {
        if (expectedNote == playedNoteQueue) {
            correct += 1
        } else {
            val vibrator: Vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        200,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            }
        }
        atempts += 1
        playedNoteQueue = ""
        CoroutineScope(Default).launch {
            updateAccuracy()
        }
        nextNote()
    }

    private fun nextNote() {
        expectedNote = pattern[currentPatternIndex]
        currentPatternIndex += 1
        currentPatternIndex %= (pattern.size)
        when (expectedNote.length) {
            1 -> {
                setMainNote(expectedNote)
                setGhostNote("")
            }
            2 -> {
                setGhostNote(expectedNote[0].toString())
                setMainNote(expectedNote[1].toString())
            }
            else -> {
                setMainNote("Error")
            }
        }
    }

    private fun setMainNote(note: String) {
        findViewById<TextView>(R.id.noteDisplay).apply {
            text = note
        }
    }

    private suspend fun updateAccuracy() {
        val total = calculateAccuracyResult()
        withContext(Main) {
            setStatusBar(total)
        }
        withContext(Main) {
            setAccuracyLabel(total)
        }
    }

    private suspend fun calculateAccuracyResult(): Double {
        var total = 0.0
        if (atempts > 0.0) {
            total = round((correct / atempts) * 10000.0) / 100
        }
        return total
    }

    private fun setStatusBar(total: Double) {
        findViewById<ProgressBar>(R.id.accuracyBar).apply {
            progress = total.toInt()
        }
    }

    private fun setAccuracyLabel(total: Double) {
        findViewById<TextView>(R.id.accuracyDisplay).apply {
            //todo remove string literal
            val accuracyString: String = getString(R.string.accuracy) + " " + total.toString() + "%"
            text = accuracyString
        }
    }

    private fun setGhostNote(note: String) {
        findViewById<TextView>(R.id.accentDisplay).apply {
            text = note
        }
    }

    /**
     * Dialog letting user know they will be redirected
     */
    fun View.startDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@RudimentTrainer)
        val dialog: AlertDialog = builder.setTitle("Learn More?")
            .setMessage("You will be redirected to a trusted website")
            .setPositiveButton("OK") { _, _ ->
                val webPage: Uri = Uri.parse(link)
                val intent = Intent(Intent.ACTION_VIEW, webPage)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
            .setNegativeButton("Cancel") { _, _ ->

            }
            .create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(250, 126, 88))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(250, 126, 88))
    }
}