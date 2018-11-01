package com.droidtitan.volley.util

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.setActionBarTitle(@StringRes title: Int) {
  (activity as? AppCompatActivity)?.supportActionBar?.setTitle(title)
}

fun Fragment.showSnackbar(message: String) {
  val view = view as View
  Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun String.firstToUpperCase() = if (isEmpty()) this else first().toUpperCase() + substring(1)