@file:JvmName("DateUtils")

package ru.axdar.notekotlin.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ildar2244 on 18.09.2018.
 */

fun formatDate(date: Date?): String {
    var dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return dateFormat.format(date)
}