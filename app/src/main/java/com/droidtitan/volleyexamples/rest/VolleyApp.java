package com.droidtitan.volleyexamples.rest;

import android.app.Application;
import com.droidtitan.volleyexamples.rest.module.RestModule;
import dagger.ObjectGraph;

import java.util.Arrays;

public class VolleyApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules());
    }

    public void inject(Object target) {
        objectGraph.inject(target);
    }

    public Object[] getModules() {
        return Arrays.asList(new RestModule(this)).toArray();
    }
}
