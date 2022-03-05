package com.example.test.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.test.R

class MainActivity : Activity(), View.OnClickListener {
    private lateinit var btn1: TextView
    private lateinit var btn2: TextView
    private lateinit var btn3: TextView
    private lateinit var btn4: TextView
    private lateinit var btn5: TextView
    private lateinit var btn6: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1 = findViewById(R.id.btn_1)
        btn2 = findViewById(R.id.btn_2)
        btn3 = findViewById(R.id.btn_3)
        btn4 = findViewById(R.id.btn_4)
        btn5 = findViewById(R.id.btn_5)
        btn6 = findViewById(R.id.btn_6)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_1 -> startActivity(TweenActivity::class.java)
            R.id.btn_2 -> startActivity(InnerFirestoneActivity::class.java)
            R.id.btn_3 -> startActivity(ExternalStorageActivity::class.java)
            R.id.btn_4 -> startActivity(DatabaseStorageActivity::class.java)
            R.id.btn_5 -> startActivity(ContentResolverActivity::class.java)
            R.id.btn_6 -> startActivity(PropertyActivity::class.java)
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