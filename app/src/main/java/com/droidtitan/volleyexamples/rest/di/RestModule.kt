package com.droidtitan.volleyexamples.rest.di

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.util.LruCache
import com.android.volley.RequestQueue
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageLoader.ImageCache
import com.android.volley.toolbox.Volley
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.OkUrlFactory
import dagger.Module
import dagger.Provides
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Named
import javax.inject.Singleton

Module
public class RestModule(val context: Context) {

    Provides Singleton Named("App")
    public fun provideAppContext(): Context = context

    Singleton Provides
    public fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    Singleton Provides
    public fun provideOkUrlFactory(okHttpClient: OkHttpClient): OkUrlFactory = OkUrlFactory(okHttpClient)

    Provides Singleton
    public fun provideQueue(okUrlFactory: OkUrlFactory, Named("App") context: Context): RequestQueue {
        return Volley.newRequestQueue(context, OkHttpStack(okUrlFactory))
    }

    Provides Singleton
    public fun provideImageLoader(queue: RequestQueue): ImageLoader {
        val imageSize = 1024L
        val count = 8L
        val maxSize = (Runtime.getRuntime().maxMemory() / imageSize / count).toInt()
        return ImageLoader(queue, LruBitmapCache(maxSize))
    }

    class OkHttpStack(val okUrlFactory: OkUrlFactory) : HurlStack() {
        @throws(IOException::class) override fun createConnection(url: URL): HttpURLConnection {
            return okUrlFactory.open(url)
        }
    }

    class LruBitmapCache(maxSize: Int) : LruCache<String, Bitmap>(maxSize), ImageCache {
        override fun sizeOf(key: String?, value: Bitmap?) = value!!.getRowBytes() * value.getHeight()
        override fun getBitmap(url: String): Bitmap? = get(url)
        override fun putBitmap(url: String, bitmap: Bitmap) {
            put(url, bitmap)
        }
    }
}