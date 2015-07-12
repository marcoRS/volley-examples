package com.droidtitan.volleyexamples.rest.di;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.droidtitan.volleyexamples.rest.util.LruBitmapCache;
import com.droidtitan.volleyexamples.rest.util.OkHttpStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {

    private Context context;

    public RestModule(Context appContext) {
        context = appContext;
    }

    @Provides
    @Named("App")
    @Singleton
    public Context provideAppContext() {
        return context;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    public OkUrlFactory provideOkUrlFactory(OkHttpClient okHttpClient) {
        return new OkUrlFactory(okHttpClient);
    }

    @Provides
    @Singleton
    public RequestQueue provideRequestQueue(OkUrlFactory okUrlFactory,
                                            @Named("App") Context context) {


        /** Set up to use OkHttp */
        return Volley.newRequestQueue(context, new OkHttpStack(okUrlFactory));
    }

    @Provides
    @Singleton
    public ImageLoader provideImageLoader(RequestQueue requestQueue) {
        final int imgSize = 1024;
        final int count = 8;
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / imgSize / count);
        return new ImageLoader(requestQueue, new LruBitmapCache(maxSize));
    }
}