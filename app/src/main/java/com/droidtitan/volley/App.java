package com.droidtitan.volley;

import android.app.Application;

import com.droidtitan.volley.di.AppComponent;
import com.droidtitan.volley.di.RestModule;
import com.droidtitan.volley.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().restModule(new RestModule(this)).build();
    }

    public AppComponent appComponent() {
        return component;
    }
}
