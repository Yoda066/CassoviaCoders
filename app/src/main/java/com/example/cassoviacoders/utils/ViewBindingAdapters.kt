package com.example.cassoviacoders.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.cassoviacoders.R
import com.example.cassoviacoders.db.Location
import com.example.cassoviacoders.retrofit.Weather
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

@BindingAdapter("android:src")
fun setImageResourceByState(imageView: ImageView, weather: Weather?) {
    weather ?: return

    val iconRes =
        if (weather.id == 800) R.drawable.ic_weather_state_sunny else {
            when (weather.id / 100) {
                3, 7 -> R.drawable.ic_weather_state_hazy //drizzle / atmosfere
                else -> R.drawable.ic_weather_state_cloudy //thunderstorm ,rain ,snow ,clouds
            }
        }

    imageView.setImageResource(iconRes)
}
