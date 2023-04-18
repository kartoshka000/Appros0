package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityTestactBinding

class testact : AppCompatActivity() {
    private lateinit var binding: ActivityTestactBinding
    private val TAG = "MyLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTestactBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testact)

        val textView = TextView(this@testact)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        textView.layoutParams = params
        textView.text = "Hello world"
        textView.textSize = 100F
        binding.test.addView(textView)
        Log.d(TAG, "teastact работает")
        val bt = Button(this)
        bt.layoutParams = params
        binding.test1.addView(bt)
    }
}