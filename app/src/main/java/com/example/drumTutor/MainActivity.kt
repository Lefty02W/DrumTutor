package com.example.drumTutor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
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


}
