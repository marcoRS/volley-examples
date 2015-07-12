package com.droidtitan.volleyexamples.rest.util

import android.graphics.Bitmap
import android.support.v4.util.LruCache
import com.android.volley.toolbox.ImageLoader.ImageCache

public class LruBitmapCache(maxSize: Int) : LruCache<String, Bitmap>(maxSize), ImageCache {

    override fun sizeOf(key: String?, value: Bitmap?) = value!!.getRowBytes() * value.getHeight()

    override fun getBitmap(url: String): Bitmap? = get(url)

    override fun putBitmap(url: String, bitmap: Bitmap) {
        put(url, bitmap)
    }
}