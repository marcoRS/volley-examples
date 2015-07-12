package com.droidtitan.volleyexamples.rest.util

import com.android.volley.toolbox.HurlStack
import com.squareup.okhttp.OkUrlFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

public class OkHttpStack(val okUrlFactory: OkUrlFactory) : HurlStack() {
    throws(IOException::class)
    override fun createConnection(url: URL): HttpURLConnection {
        return okUrlFactory.open(url)
    }
}
