package com.droidtitan.volley

import android.graphics.Bitmap
import androidx.collection.LruCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.OkUrlFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

val restModule = module {

  single {
    val factory = OkUrlFactory(OkHttpClient())

    val hurlStack = object : HurlStack() {
      @Throws(IOException::class)
      override fun createConnection(url: URL): HttpURLConnection {
        return factory.open(url)
      }
    }

    Volley.newRequestQueue(androidApplication(), hurlStack)
  }

  single {
    val imageSize = 1024L
    val count = 8L
    val maxSize = (Runtime.getRuntime().maxMemory() / imageSize / count).toInt()

    val lruCache = object : LruCache<String, Bitmap>(maxSize), ImageLoader.ImageCache {
      override fun sizeOf(key: String, value: Bitmap) = value.rowBytes * value.height
      override fun getBitmap(url: String): Bitmap? = get(url)
      override fun putBitmap(url: String, bitmap: Bitmap) = put(url, bitmap).let { }
    }

    ImageLoader(get(), lruCache)
  }
}