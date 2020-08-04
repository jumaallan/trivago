package com.trivago.core.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Responsible for creating a snackBar on a view
 *
 * @param message
 */
fun View.makeSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
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
