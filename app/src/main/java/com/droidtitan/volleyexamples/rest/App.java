package com.droidtitan.volleyexamples.rest;

import android.app.Application;
import android.content.Context;

import com.droidtitan.volleyexamples.rest.di.AppComponent;
import com.droidtitan.volleyexamples.rest.di.DaggerAppComponent;
import com.droidtitan.volleyexamples.rest.di.RestModule;

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

    public static App from(Context context) {
        return (App) context.getApplicationContext();
    }
}
