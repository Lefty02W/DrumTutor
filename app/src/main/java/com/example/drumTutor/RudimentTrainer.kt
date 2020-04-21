package com.example.drumTutor

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
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
        updateAccuracy()
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
        }
        atempts += 1
        playedNoteQueue = ""
        updateAccuracy()
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

    private fun updateAccuracy() {
        var total = 0.0
        if (atempts > 0.0) {
            total = round((correct / atempts) * 10000.0) / 100
        }
        findViewById<ProgressBar>(R.id.accuracyBar).apply {
            progress = total.toInt()
        }

        findViewById<TextView>(R.id.accuracyDisplay).apply {
            //todo remove string literal
            text = "Accuracy: $total%"
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
    fun startDialog(view: View) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
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