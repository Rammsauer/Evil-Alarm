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
            startActivity(Intent(this, LockActivity::class.java).putExtra(Constants.BARCODE_KEY, sharedPreferences.getString(Constants.BARCODE_KEY, null)))
            finish()
        } else {
            Log.i(javaClass.simpleName, "Settings")
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        }


        Log.i(javaClass.simpleName, "State: ${sharedPreferences.getBoolean(Constants.IS_HOT, false)};  ${sharedPreferences.getString(Constants.BARCODE_KEY, null)}")


    }
}
