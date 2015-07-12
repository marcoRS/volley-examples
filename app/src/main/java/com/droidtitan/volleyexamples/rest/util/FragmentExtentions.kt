package com.droidtitan.volleyexamples.rest.util

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.droidtitan.volleyexamples.rest.App
import com.droidtitan.volleyexamples.rest.di.AppComponent

fun Fragment.setActionBarTitle(@StringRes title: Int) {
    (getActivity() as? AppCompatActivity)?.getSupportActionBar()?.setTitle(title)
}

fun Fragment.withComponent(): AppComponent {
    return (getActivity().getApplicationContext() as App).appComponent()
}

fun Fragment.toast(message: String) = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show()

fun String.firstToUpperCase(): String = first().toUpperCase() + substring(1)