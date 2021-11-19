package com.uni.alarm

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*

class QRScannerActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPreferences: SharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)

        requestPermissions(arrayOf(Manifest.permission.CAMERA), 42);

        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)


        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                sharedPreferences.edit().putString(Constants.BARCODE_KEY, it.text).commit()
                Log.i(javaClass.simpleName, "QR ${it.text}")
                Log.i(javaClass.simpleName, "State: ${sharedPreferences.getBoolean(Constants.IS_HOT, false)};  ${sharedPreferences.getString(Constants.BARCODE_KEY, null)}")
                finish()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }
}