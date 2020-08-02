package com.trivago.core.utils

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList

/**
 * View Utils
 *
 * These are various extension functions used across the application. Easily re-usable
 */

/**
 * Responsible for dismissing the keyboard
 *
 * @param windowToken
 */
fun Context.dismissKeyboard(windowToken: IBinder) {
    val imm = getSystemService<InputMethodManager>()
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Responsible for creating a toast
 *
 * @param message
 * @param duration
 */
fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

/**
 * Responsible for creating a snackBar on a view
 *
 * @param message
 */
fun View.makeSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

/**
 * Responsible for creating a snackBar on an Activity
 *
 * @param message
 */
fun Activity.makeSnackbar(message: String) {
    findViewById<View>(android.R.id.content).makeSnackbar(message)
}

/**
 * Responsible for showing a view, toggle visibility to View.VISIBLE
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Responsible for hiding a view, toggle visibility to View.GONE
 */
fun View.hide() {
    visibility = View.GONE
}

/**
 * Responsible for forcing an http url to https
 *
 * @return the url in https, if an http url format is passed
 */
fun String.toHttps(): String =
    if (!this.contains("https")) {
        this.replace("http", "https")
    } else this

/**
 * Responsible for converting Flow<List<T>> to a List<>
 *
 * @param T
 * @return a List from a Flow<List<>>
 */
@FlowPreview
suspend fun <T> Flow<List<T>>.flattenToList() =
    flatMapConcat { it.asFlow() }.toList()