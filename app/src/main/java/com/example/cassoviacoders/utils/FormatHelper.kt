package com.example.cassoviacoders.utils

import java.text.SimpleDateFormat
import java.util.*

class FormatHelper {

    companion object {
        private val TIME = SimpleDateFormat("hh:mm", Locale.getDefault())
        private val MainDATETIME = SimpleDateFormat("EEEE, dd MMM yyyy | hh:mma", Locale.US)

        fun formatTime(time: Date?) : String {
            time?.let {
                return TIME.format(it)
            }
            return ""
        }

        fun formatMainDate(time: Date?) : String {
            time?.let {
                return MainDATETIME.format(it)
            }
            return ""
        }
    }
}