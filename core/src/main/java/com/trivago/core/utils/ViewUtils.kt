package com.trivago.core.utils

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import com.google.android.material.snackbar.Snackbar

fun Context.dismissKeyboard(windowToken: IBinder) {
    val imm = getSystemService<InputMethodManager>()
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun View.makeSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun Activity.makeSnackbar(message: String) {
    findViewById<View>(android.R.id.content).makeSnackbar(message)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun String.toHttps(): String =
    if (!this.contains("https")) {
        this.replace("http", "https")
    } else this