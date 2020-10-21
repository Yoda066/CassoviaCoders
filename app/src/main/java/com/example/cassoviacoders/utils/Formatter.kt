package com.example.cassoviacoders.utils

import java.text.Normalizer
import java.util.*
import java.util.regex.Pattern


val replaceAccentsPattern =
    Pattern.compile("[\\p{InCombiningDiacriticalMarks}]")

/**
 * Odstráni diakritiku z textu a vráti reťazec.
 *
 * @param text ak null tak vráti null inak vráti reťazec bez diakritiky.
 * @return vráti reťazec bez diakritiky. null ak text je null.
 */
fun String?.formatForSearch(): String {
    if (this == null) return ""
    Normalizer.normalize(this, Normalizer.Form.NFD).let {
        return replaceAccentsPattern.matcher(it).replaceAll("").toLowerCase(Locale.getDefault())
    }
}