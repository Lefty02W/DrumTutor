package com.example.drumTutor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnOpenMetronomeActivity: Button = findViewById(R.id.metronome_btn)
        val btnOpenRudimentsHome: Button = findViewById(R.id.rudiment_btn)
        btnOpenMetronomeActivity.setOnClickListener {
            val openMetronome = Intent(this, Metronome::class.java)
            startActivity(openMetronome)
        }

        btnOpenRudimentsHome.setOnClickListener {
            val openRudimentsHome = Intent(this, RudimentsHome::class.java)
            startActivity(openRudimentsHome)
        }
        persistDefaultSharedPreferences()
        readPersistedNumber()
    }

    /**
     *  Retrieves app's default shared preferences and persists a number
     */
    private fun persistDefaultSharedPreferences() {
        val score = Math.random() * 100
        val defaultSharedPreferences =
            this.getSharedPreferences("defaultSharedPreferences", Context.MODE_PRIVATE)
        with (defaultSharedPreferences.edit()) {
            putInt("Score", score.toInt())
            commit()
        }
    }

    /**
     * reads persisted defult shared preferences number
     */
    private fun readPersistedNumber() {
        val defaultSharedPreferences =
            this.getSharedPreferences("defaultSharedPreferences", Context.MODE_PRIVATE)
        val score = defaultSharedPreferences.getInt("Score", 0)
        println("#########################################")
        println(score)
        println("#########################################")

    }


    fun sendMessage(view: View){
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    companion object {
        const val EXTRA_MESSAGE = "com.example.drumTutor.MESSAGE"
    }
}
