package com.droidtitan.volleyexamples.rest.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = true)
public class ContextModule {

    private final Context appContext;

    public ContextModule(Context context) {
        appContext = context;
    }

    @Provides
    public Context provideAppContext() {
        return appContext;
    }
}
