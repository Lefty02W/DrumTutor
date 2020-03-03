package com.example.drumTutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

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
    }



    fun sendMessage(view: View){
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(Companion.EXTRA_MESSAGE, message)
        }
        startActivity(intent)

    }

    companion object {
        const val EXTRA_MESSAGE = "com.example.drumTutor.MESSAGE"
    }
}
