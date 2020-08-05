package com.trivago.core.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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


fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}