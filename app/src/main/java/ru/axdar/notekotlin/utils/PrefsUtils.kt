@file:JvmName("PrefsUtils")

package ru.axdar.notekotlin.utils

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.Delegates

/**
 * Created by ildar2244 on 24.09.2018.
 */

private var mPrefs by Delegates.notNull<SharedPreferences>()

fun initPrefs(context: Context) {
    mPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
}

fun getNotesSortMethodName(defaultValue: String): String = mPrefs.getString("sort_method", defaultValue)

fun setNotesSortMethod(sortMethod: String) {
    mPrefs.edit().putString("sort_method", sortMethod).apply()
}