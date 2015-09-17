package com.droidtitan.volley.di

import android.app.Application
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
import javax.inject.Singleton

@Module
public class RestModule(val application: Application) {

    @Singleton @Provides
    public fun provideQueue(): RequestQueue {

        val factory = OkUrlFactory(OkHttpClient())
        val hurlStack = object : HurlStack() {
            @Throws(IOException::class) override fun createConnection(url: URL): HttpURLConnection {
                return factory.open(url)
            }
        }

        return Volley.newRequestQueue(application, hurlStack)
    }

    @Singleton @Provides
    public fun provideImageLoader(queue: RequestQueue): ImageLoader {

        val imageSize = 1024L
        val count = 8L
        val maxSize = (Runtime.getRuntime().maxMemory() / imageSize / count).toInt()

        val lruCache = object : LruCache<String, Bitmap>(maxSize), ImageCache {
            override fun sizeOf(key: String?, value: Bitmap?) = value!!.rowBytes * value.height
            override fun getBitmap(url: String): Bitmap? = get(url)
            override fun putBitmap(url: String, bitmap: Bitmap) = put(url, bitmap).let {  }
        }

        return ImageLoader(queue, lruCache)
    }
}