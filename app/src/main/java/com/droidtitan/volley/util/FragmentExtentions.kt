package com.droidtitan.volley.util

import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.droidtitan.volley.App
import com.droidtitan.volley.di.AppComponent

fun Fragment.setActionBarTitle(@StringRes title: Int) {
  (activity as? AppCompatActivity)?.supportActionBar?.setTitle(title)
}

fun Fragment.withComponent(): AppComponent = (activity?.application as App).component()

fun Fragment.showSnackbar(message: String) {
  val view = view as View
  Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun String.firstToUpperCase() = if (isEmpty()) this else first().toUpperCase() + substring(1)