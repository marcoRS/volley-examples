package com.droidtitan.volleyexamples.rest.module;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.droidtitan.volleyexamples.rest.fragment.ImageLoaderFragment;
import com.droidtitan.volleyexamples.rest.fragment.NetworkImageFragment;
import com.droidtitan.volleyexamples.rest.fragment.RequestFragment;
import com.droidtitan.volleyexamples.rest.util.LruBitmapCache;
import com.droidtitan.volleyexamples.rest.util.OkHttpStack;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module(injects = {RequestFragment.class, NetworkImageFragment.class, ImageLoaderFragment.class},
        library = true, complete = false)
public class RestModule {

    private Context context;

    public RestModule(Context appContext) {
        context = appContext;
    }

    @Provides
    @Named("App")
    public Context provideAppContext() {
        return context;
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public RequestQueue provideRequestQueue(OkHttpClient okHttpClient, @Named("App") Context context) {
        /** Set up to use OkHttp */
        return Volley.newRequestQueue(context, new OkHttpStack(okHttpClient));
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
