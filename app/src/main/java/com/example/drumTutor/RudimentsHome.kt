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

    private lateinit var sourcesPicker: Spinner
    //todo remove spinner
    private lateinit var rudimentPicker: RecyclerView

    private var rudiments = arrayOf<Rudiment>(
        Rudiment(
            "Single Stroke",
            arrayOf("R", "L", "R", "L", "R", "L", "R", "L"),
            "https://vicfirth.zildjian.com/education/01-single-stroke-roll.html"
        ),
        Rudiment("Double Stroke", arrayOf("R", "R", "L", "L", "R", "R", "L", "L"), null),
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
        set(value) {
            field = value
        }


//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        listAdapter = ArrayAdapter<Rudiment>(this, R.layout.simple_list_item_1, rudiments)
//        val permission = arrayOf(Manifest.permission.CALL_PHONE)
//        if (!hasPermissions(permission)) {
//            requestPermissions(permission, 1)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rudiment_list)
        rudimentPicker = findViewById(R.id.rudimentPicker)
        val layoutManager = LinearLayoutManager(this)
        rudimentPicker.layoutManager = layoutManager
        rudimentPicker.adapter = RudimentAdapter(this, rudiments)

//        var rudiments: List<Rudiment> = listOf()
    }


//    override fun onPostExecute() {
//        super.onPostExecute(rudiments)
//        context.get()?.rudiments = rudiments
//    }





    @RequiresApi(Build.VERSION_CODES.M)
    fun hasPermissions(permission: Array<String>): Boolean {
        return permission.all { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
    }

//    override fun onListItemClick(l: ListView?, v: View?, rudimentId: Int, id: Long) {
//        val options = arrayOf("Play", "More Info")
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Explore Further?")
//        builder.setItems(options) { _, optionId ->
//            dispatchAction(optionId, rudiments[rudimentId])
//        }
//        builder.show()
//    }

    private fun dispatchAction(optionId: Int, rudiment: Rudiment) {
        when (optionId) {
            0 -> {
                val openRudimentTrainer = Intent(this, RudimentTrainer::class.java)
                openRudimentTrainer.putExtra("name", rudiment.name)
                openRudimentTrainer.putExtra("pattern", rudiment.pattern)
                startActivity(openRudimentTrainer)
            }
            1 -> {
                val webPage: Uri = Uri.parse(rudiment.link)
                val intent = Intent(Intent.ACTION_VIEW, webPage)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
    }



}
