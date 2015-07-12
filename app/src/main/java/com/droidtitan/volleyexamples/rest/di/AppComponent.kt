package com.droidtitan.volleyexamples.rest.di

import com.droidtitan.volleyexamples.rest.fragment.GsonRequestFragment
import com.droidtitan.volleyexamples.rest.fragment.ImageLoaderFragment
import com.droidtitan.volleyexamples.rest.fragment.NetworkImageFragment
import dagger.Component
import javax.inject.Singleton

Singleton
Component(modules = arrayOf(RestModule::class))
public interface AppComponent {
    public fun inject(f: ImageLoaderFragment)

    public fun inject(f: NetworkImageFragment)

    public fun inject(f: GsonRequestFragment)
}
