package com.droidtitan.volleyexamples.rest.module;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.droidtitan.volleyexamples.rest.fragment.RequestFragment;
import com.droidtitan.volleyexamples.rest.util.LruBitmapCache;
import com.droidtitan.volleyexamples.rest.util.OkHttpStack;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(injects = RequestFragment.class, library = true, complete = false, includes = ContextModule.class)
public class RestModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public RequestQueue provideRequestQueue(OkHttpClient okHttpClient, Context context) {
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
