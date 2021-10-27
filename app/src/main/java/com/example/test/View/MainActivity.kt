package com.example.test.View

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var btn1: TextView? = null
    private var btn2: TextView? = null
    private var btn3: TextView? = null
    private var btn4: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1 = findViewById(R.id.btn_1)
        btn2 = findViewById(R.id.btn_2)
        btn3 = findViewById(R.id.btn_3)
        btn4 = findViewById(R.id.btn_4)
        btn1?.setOnClickListener(this)
        btn2?.setOnClickListener(this)
        btn3?.setOnClickListener(this)
        btn4?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> startActivity(TweenActivity::class.java)
            R.id.btn_2 -> startActivity(InnerFilestorageActivity::class.java)
            R.id.btn_3 -> startActivity(ExteralStorageActivity::class.java)
            R.id.btn_4 -> startActivity(DatabaseStorageActivity::class.java)
        }
    }

    private fun startActivity(targetCls: Class<*>) {
        val intent = Intent(this, targetCls)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}