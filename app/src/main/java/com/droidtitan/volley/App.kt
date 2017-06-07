package com.droidtitan.volley

import android.app.Application

import com.droidtitan.volley.di.AppComponent
import com.droidtitan.volley.di.DaggerAppComponent
import com.droidtitan.volley.di.RestModule

class App : Application() {

  lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()
    component = DaggerAppComponent.builder().restModule(RestModule(this)).build()
  }

  fun component(): AppComponent {
    return component
  }
}