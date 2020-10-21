package com.example.cassoviacoders.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.cassoviacoders.db.Location
import java.util.*
import kotlin.math.roundToInt

@BindingAdapter("android:text")
fun TextView.setText(location: Location?) {
    text = location?.getFormattedTitle() ?: ""
}

@BindingAdapter("degreeValue")
fun TextView.setValue(value: Double) {
    text = String.format("%s°C", value.roundToInt())
}

@BindingAdapter("rounded")
fun TextView.setRoundedValue(value: Double?) {
    text = value?.roundToInt()?.toString() ?: ""
}

@BindingAdapter("niceDate")
fun TextView.setValue(value: Date?) {
    text = value?.toString() ?: "???"
}
