package com.droidtitan.volley.util

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.droidtitan.volley.App
import com.droidtitan.volley.di.AppComponent

fun Fragment.setActionBarTitle(@StringRes title: Int) {
    (getActivity() as? AppCompatActivity)?.getSupportActionBar()?.setTitle(title)
}

fun Fragment.withComponent(): AppComponent = (getActivity().getApplication() as App).component()

fun Fragment.toast(message: String) = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show()

fun String.firstToUpperCase() = if (isEmpty()) this else first().toUpperCase() + substring(1)