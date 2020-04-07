package com.example.drumTutor

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RudimentTrainer : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rudiment_trainer)
        val pattern = intent?.extras?.get("pattern")
        val textView = findViewById<TextView>(R.id.rudimentHeading).apply {
            text = intent.extras?.get("name").toString()
        }
        val leftStick: Button = findViewById(R.id.leftStickBtn)
        val rightStick: Button = findViewById(R.id.rightStickBtn)
    }


}