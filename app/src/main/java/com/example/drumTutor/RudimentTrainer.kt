package com.example.drumTutor

import android.app.Activity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rudiment_trainer)
        pattern = intent?.extras?.get("pattern") as Array<String>
        updateAccuracy()
        nextNote()
        findViewById<TextView>(R.id.rudimentHeading).apply {
            text = intent.extras?.get("name").toString()
        }

        val leftStick: Button = findViewById(R.id.leftStickBtn)
        val rightStick: Button = findViewById(R.id.rightStickBtn)

        leftStick.setOnClickListener {
            playedNoteQueue += "L"
            println(playedNoteQueue)
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
        println(note)
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

}