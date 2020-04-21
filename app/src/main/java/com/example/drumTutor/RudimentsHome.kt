package com.example.drumTutor

import android.app.Activity
//import android.R
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RudimentsHome : Activity() {

    private lateinit var rudimentPicker: RecyclerView

    private var rudiments = arrayOf(
        Rudiment(
            "Single Stroke",
            arrayOf("R", "L", "R", "L", "R", "L", "R", "L"),
            "https://vicfirth.zildjian.com/education/01-single-stroke-roll.html"
        ),
        Rudiment(
            "Double Stroke",
            arrayOf("R", "R", "L", "L", "R", "R", "L", "L"),
            "https://www.40drumrudiments.com/double-stroke-roll/"
        ),
        Rudiment(
            "Single Paradiddle",
            arrayOf("R", "L", "R", "R", "L", "R", "L", "L"),
            "https://vicfirth.zildjian.com/education/16-single-paradiddle.html"
        ),
        Rudiment(
            "Double Paradiddle",
            arrayOf("R", "L", "R", "L", "R", "R", "L", "R", "L", "R", "L", "L"),
            "https://vicfirth.zildjian.com/education/17-double-paradiddle.html"
        ),
        Rudiment(
            "Tripple Paradiddle",
            arrayOf("R", "L", "R", "L", "R", "L", "R", "R", "L", "R", "L", "R", "L", "R", "L", "L"),
            "https://vicfirth.zildjian.com/education/18-triple-paradiddle.html"
        ),
        Rudiment(
            "Paradiddle-diddle",
            arrayOf("R", "L", "R", "R", "L", "L", "L", "R", "L", "L", "R", "R"),
            "https://vicfirth.zildjian.com/education/19-paradiddle-diddle.html"
        ),
        Rudiment(
            "Flam",
            arrayOf("LR", "RL"),
            "https://vicfirth.zildjian.com/education/20-Flam.html"
        ),
        Rudiment(
            "Flam Accent",
            arrayOf("LR", "L", "R", "RL", "R", "L"),
            "https://vicfirth.zildjian.com/education/21-flam-accent.html"
        ),
        Rudiment(
            "Flam Tap",
            arrayOf("LR", "R", "RL", "L", "LR", "R", "RL", "L"),
            "https://vicfirth.zildjian.com/education/22-flam-tap.html"
        ),
        Rudiment(
            "Flam Paradiddle",
            arrayOf("LR", "L", "R", "R", "RL", "R", "L", "L"),
            "https://vicfirth.zildjian.com/education/24-flam-paradiddle.html"
        ),
        Rudiment(
            "Flamacue",
            arrayOf("LR", "L", "R", "L", "LR", "RL", "R", "L", "R", "RL"),
            "https://vicfirth.zildjian.com/education/23-flamacue.html"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rudiment_list)
        rudimentPicker = findViewById(R.id.rudimentPicker)
        val layoutManager = LinearLayoutManager(this)
        rudimentPicker.layoutManager = layoutManager
        rudimentPicker.adapter = RudimentAdapter(this, rudiments) {
            val openRudimentTrainer = Intent(this, RudimentTrainer::class.java)
            openRudimentTrainer.putExtra("name", it.name)
            openRudimentTrainer.putExtra("pattern", it.pattern)
            openRudimentTrainer.putExtra("link", it.link)
            startActivity(openRudimentTrainer)
        }
    }

}
