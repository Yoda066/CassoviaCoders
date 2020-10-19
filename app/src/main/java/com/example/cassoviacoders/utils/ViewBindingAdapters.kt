package com.example.cassoviacoders.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.cassoviacoders.Location


@BindingAdapter("android:text")
fun TextView.setText(location: Location?) {
    text = location?.getFormatedTitle() ?: ""
}

@BindingAdapter("degreeValue")
fun TextView.setValue(value: String) {
    text = """$value°C"""
}
