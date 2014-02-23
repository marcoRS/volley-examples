package com.droidtitan.volleyexamples.rest;

import android.app.Application;
import com.droidtitan.volleyexamples.rest.module.ContextModule;
import com.droidtitan.volleyexamples.rest.module.RestModule;
import dagger.ObjectGraph;

import java.util.Arrays;
import java.util.List;

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
        List<Object> modules = Arrays.asList(new RestModule(), new ContextModule(this));

        return modules.toArray();
    }
}
