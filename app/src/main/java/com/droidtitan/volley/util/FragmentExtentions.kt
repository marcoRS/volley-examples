package com.droidtitan.volley.util

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.droidtitan.volley.App
import com.droidtitan.volley.di.AppComponent

fun Fragment.setActionBarTitle(@StringRes title: Int) {
    (activity as? AppCompatActivity)?.supportActionBar?.setTitle(title)
}

fun Fragment.withComponent(): AppComponent = (activity.application as App).component()

fun Fragment.showSnackbar(message: String) = Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

fun String.firstToUpperCase() = if (isEmpty()) this else first().toUpperCase() + substring(1)