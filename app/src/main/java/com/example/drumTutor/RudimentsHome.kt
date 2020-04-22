package com.example.drumTutor

import android.app.Activity
//import android.R
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RudimentsHome : Activity() {
    private lateinit var rudimentPicker: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rudiment_list)
        val rudiments: Array<Rudiment> = setRudimentList()
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

    private fun setRudimentList(): Array<Rudiment> {
        return arrayOf(
            Rudiment(
                resources.getString(R.string.single_paradiddle),
                arrayOf("R", "L", "R", "L", "R", "L", "R", "L"),
                "https://vicfirth.zildjian.com/education/01-single-stroke-roll.html"
            ),
            Rudiment(
                resources.getString(R.string.double_paradiddle),
                arrayOf("R", "R", "L", "L", "R", "R", "L", "L"),
                "https://www.40drumrudiments.com/double-stroke-roll/"
            ),
            Rudiment(
                resources.getString(R.string.single_paradiddle),
                arrayOf("R", "L", "R", "R", "L", "R", "L", "L"),
                "https://vicfirth.zildjian.com/education/16-single-paradiddle.html"
            ),
            Rudiment(
                resources.getString(R.string.double_paradiddle),
                arrayOf("R", "L", "R", "L", "R", "R", "L", "R", "L", "R", "L", "L"),
                "https://vicfirth.zildjian.com/education/17-double-paradiddle.html"
            ),
            Rudiment(
                resources.getString(R.string.triple_paradiddle),
                arrayOf(
                    "R",
                    "L",
                    "R",
                    "L",
                    "R",
                    "L",
                    "R",
                    "R",
                    "L",
                    "R",
                    "L",
                    "R",
                    "L",
                    "R",
                    "L",
                    "L"
                ),
                "https://vicfirth.zildjian.com/education/18-triple-paradiddle.html"
            ),
            Rudiment(
                resources.getString(R.string.paradiddle_diddle),
                arrayOf("R", "L", "R", "R", "L", "L", "L", "R", "L", "L", "R", "R"),
                "https://vicfirth.zildjian.com/education/19-paradiddle-diddle.html"
            ),
            Rudiment(
                resources.getString(R.string.flam),
                arrayOf("LR", "RL"),
                "https://vicfirth.zildjian.com/education/20-Flam.html"
            ),
            Rudiment(
                resources.getString(R.string.flam_accent),
                arrayOf("LR", "L", "R", "RL", "R", "L"),
                "https://vicfirth.zildjian.com/education/21-flam-accent.html"
            ),
            Rudiment(
                resources.getString(R.string.flam_tap),
                arrayOf("LR", "R", "RL", "L", "LR", "R", "RL", "L"),
                "https://vicfirth.zildjian.com/education/22-flam-tap.html"
            ),
            Rudiment(
                resources.getString(R.string.flam_paradiddle),
                arrayOf("LR", "L", "R", "R", "RL", "R", "L", "L"),
                "https://vicfirth.zildjian.com/education/24-flam-paradiddle.html"
            ),
            Rudiment(
                resources.getString(R.string.flamacue),
                arrayOf("LR", "L", "R", "L", "LR", "RL", "R", "L", "R", "RL"),
                "https://vicfirth.zildjian.com/education/23-flamacue.html"
            )
        )
    }

}
