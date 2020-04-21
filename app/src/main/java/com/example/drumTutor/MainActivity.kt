package com.example.drumTutor

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runAnimation()

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

    override fun onResume() {
        runAnimation()
        super.onResume()
    }

    override fun onStart() {
        runAnimation()
        super.onStart()
    }

    private fun runAnimation() {
        val ttb: Animation = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val ttb2: Animation = AnimationUtils.loadAnimation(this, R.anim.ttb2)
        val stb: Animation = AnimationUtils.loadAnimation(this, R.anim.stb)

        val logo: ImageView = findViewById(R.id.logoImage)
        val rudimentBtn: TextView = findViewById(R.id.rudiment_btn)
        val metronomeBtn: TextView = findViewById(R.id.metronome_btn)

        logo.startAnimation(stb)
        rudimentBtn.startAnimation(ttb)
        metronomeBtn.startAnimation(ttb2)
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


//    fun sendMessage(view: View){
//        val editText = findViewById<EditText>(R.id.editText)
//        val message = editText.text.toString()
//        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
//            putExtra(EXTRA_MESSAGE, message)
//        }
//        startActivity(intent)
//    }

    companion object {
        const val EXTRA_MESSAGE = "com.example.drumTutor.MESSAGE"
    }
}
