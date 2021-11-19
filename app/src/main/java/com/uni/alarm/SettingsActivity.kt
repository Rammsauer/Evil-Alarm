package com.uni.alarm

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences: SharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)
        button2.setOnClickListener {
            startActivity(Intent(this, QRScannerActivity::class.java))
        }
        button3.setOnClickListener {
            sharedPreferences.edit().putBoolean(Constants.IS_HOT, true).commit()
            Log.i(javaClass.simpleName, "State: ${sharedPreferences.getBoolean(Constants.IS_HOT, false)};  ${sharedPreferences.getString(Constants.BARCODE_KEY, null)}")

        }
    }


}