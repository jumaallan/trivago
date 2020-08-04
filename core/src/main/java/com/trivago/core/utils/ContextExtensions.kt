package com.trivago.core.utils

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

/**
 * Responsible for dismissing the keyboard
 *
 * @param windowToken
 */
fun Context.dismissKeyboard(windowToken: IBinder) {
    val imm = getSystemService<InputMethodManager>()
    imm?.hideSoftInputFromWindow(windowToken, 0)
}