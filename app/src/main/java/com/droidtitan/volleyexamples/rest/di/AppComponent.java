package com.droidtitan.volleyexamples.rest.di;

import com.droidtitan.volleyexamples.rest.fragment.ImageLoaderFragment;
import com.droidtitan.volleyexamples.rest.fragment.NetworkImageFragment;
import com.droidtitan.volleyexamples.rest.fragment.RequestFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RestModule.class)
public interface AppComponent {
    void inject(ImageLoaderFragment fragment);

    void inject(NetworkImageFragment fragment);

    void inject(RequestFragment fragment);
}
