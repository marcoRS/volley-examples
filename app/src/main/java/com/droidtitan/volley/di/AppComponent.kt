package com.droidtitan.volley.di

import com.droidtitan.volley.fragment.GsonRequestFragment
import com.droidtitan.volley.fragment.ImageLoaderFragment
import com.droidtitan.volley.fragment.NetworkImageFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RestModule::class)) interface AppComponent {
  fun inject(f: ImageLoaderFragment)

  fun inject(f: NetworkImageFragment)

  fun inject(f: GsonRequestFragment)
}
