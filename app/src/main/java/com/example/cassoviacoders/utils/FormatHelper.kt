package com.example.cassoviacoders.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class FormatHelper {

    companion object {
        private val TIME = SimpleDateFormat("hh:mm", Locale.getDefault())
        private val MAIN_DATETIME = SimpleDateFormat("EEEE, dd MMM yyyy | hh:mma", Locale.US)
        private val DAILY_DATE = SimpleDateFormat("EEE, dd", Locale.US)

        fun formatTime(time: Date?): String {
            time?.let {
                return TIME.format(it)
            }
            return ""
        }

        fun formatMainDate(time: Date?): String {
            time?.let {
                return MAIN_DATETIME.format(it)
            }
            return ""
        }

        fun formatDailyDate(time: Date?): String {
            time?.let {
                return DAILY_DATE.format(it)
            }
            return ""
        }

        //        vrati poradove cislo dna podla milisekund
        fun getDay(milis: Long = System.currentTimeMillis()): Int =
            TimeUnit.MILLISECONDS.toDays(milis).toInt()
    }
}