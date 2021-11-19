package com.uni.alarm

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!sharedPreferences.getString(Constants.BARCODE_KEY, null).equals(null) &&
            sharedPreferences.getBoolean(Constants.IS_HOT, false)
        ) {
            Log.i(javaClass.simpleName, "Lock")
        } else {
            Log.i(javaClass.simpleName, "Settings")
            startActivity(Intent(this, SettingsActivity::class.java))
        }


        Log.i(javaClass.simpleName, "State: ${sharedPreferences.getBoolean(Constants.IS_HOT, false)};  ${sharedPreferences.getString(Constants.BARCODE_KEY, null)}")


    }
}
