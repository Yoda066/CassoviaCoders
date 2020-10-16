package com.example.cassoviacoders

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.cassoviacoders.databinding.ActivityFullscreenBinding
import com.example.cassoviacoders.databinding.ActivityLocationChooseBinding

class FullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLocationChooseBinding = DataBindingUtil.setContentView(this, R.layout.activity_location_choose)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}