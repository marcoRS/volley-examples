package com.droidtitan.volley.di

import com.droidtitan.volley.fragment.GsonRequestFragment
import com.droidtitan.volley.fragment.ImageLoaderFragment
import com.droidtitan.volley.fragment.NetworkImageFragment
import dagger.Component
import javax.inject.Singleton

Singleton
Component(modules = arrayOf(RestModule::class))
public interface AppComponent {
    public fun inject(f: ImageLoaderFragment)

    public fun inject(f: NetworkImageFragment)

    public fun inject(f: GsonRequestFragment)
}
