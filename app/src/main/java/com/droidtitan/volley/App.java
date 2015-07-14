package com.droidtitan.volley;

import android.app.Application;

import com.droidtitan.volley.di.AppComponent;
import com.droidtitan.volley.di.DaggerAppComponent;
import com.droidtitan.volley.di.RestModule;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        /** The class creating a component has to be written in java for Dagger 2 to work. */
        component = DaggerAppComponent.builder().restModule(new RestModule(this)).build();
    }

    public AppComponent component() {
        return component;
    }
}
