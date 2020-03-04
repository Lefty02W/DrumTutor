package com.example.drumTutor

import android.Manifest
import android.R
import android.app.AlertDialog
import android.app.ListActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import java.net.URLEncoder

class RudimentsHome : ListActivity() {

    private val friends = arrayOf<Friend>(
        Friend(
            "Ben Adams",
            "bta47",
            "Christchurch, NZ",
            "benjamin.adams@canterbury.ac.nz",
            "#######"
        ),
        Friend("Luke Walsh", "lwa383", "Auckland, NZ", "lwa383@uclive.ac.nz", "02102392306")
    )


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ArrayAdapter<Friend>(this, R.layout.simple_list_item_1, friends)
        val permission = arrayOf(Manifest.permission.CALL_PHONE)
        if (!hasPermissions(permission)) {
            requestPermissions(permission, 1)
        }
//        setContentView(R.layout.activity_rudiments_home)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasPermissions(permission: Array<String>): Boolean {
        return permission.all { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
    }

//    override fun onListItemClick(l: ListView?, v: View?, friendId: Int, id: Long) {
//        Log.d("FOO", "$friendId")
//    }

    override fun onListItemClick(l: ListView?, v: View?, friendId: Int, id: Long) {
        val options = arrayOf("Map", "Email", "Text", "Call", "Slack")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Connect how?")
        builder.setItems(options) { _, optionId ->
            dispatchAction(optionId, friends[friendId])
        }
        builder.show()
    }

    private fun dispatchAction(optionId: Int, friend: Friend) {
        when (optionId) {
            0 -> {
                val uri = Uri.parse("geo:0,0?q=${URLEncoder.encode(friend.home, "UTF-8")}")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_EMAIL, friend.email)
                startActivity(intent)
            }
            2 -> {
                val uri = Uri.parse("smsto:${friend.phone}")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(intent)

            }
            3 -> {
                val uri = Uri.parse("tel:${friend.phone}")
                val intent = Intent(Intent.ACTION_DIAL, uri)
                startActivity(intent)
            }
            4 -> TODO("Slack")
        }
    }


}
