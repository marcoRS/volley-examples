package com.droidtitan.volley.util

import com.android.volley.*
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.UnsupportedEncodingException
import java.util.HashMap

/** Volley adapter for JSON requests that will be parsed into Java objects by Gson. */
public class GsonRequest<T> : Request<T> {
    private val clazz: Class<T>
    private var httpHeaders: MutableMap<String, String>? = null
    private var listener: Listener<T>? = null

    /**
     * Make a GET request and return a parsed object from JSON.
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public constructor(url: String, clazz: Class<T>, headers: MutableMap<String, String>?, listener: Listener<T>, errorListener: ErrorListener) :
    super(Request.Method.GET, url, errorListener) {
        this.clazz = clazz
        this.httpHeaders = headers
        this.listener = listener
    }

    /**
     * Make a specified Http Request and return a parsed object from JSON.
     * @param method        The Http Method type as specified by Request.Method
     * @param url           URL of the request to make
     * @param clazz         Relevant class object, for Gson's reflection
     * @param headers       The http headers
     * @param listener      Callback interface for delivering parsed responses
     * @param errorListener Callback interface for delivering error responses
     */
    public constructor(method: Int, url: String, clazz: Class<T>, headers: MutableMap<String, String>?,
                       listener: Listener<T>, errorListener: ErrorListener) :
    super(method, url, errorListener) {
        this.clazz = clazz
        this.httpHeaders = headers
        this.listener = listener
    }

    public constructor(method: Int, url: String, clazz: Class<T>, listener: Listener<T>, e: ErrorListener) :
    super(method, url, e) {
        this.clazz = clazz
        this.listener = listener
    }

    @throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        if (httpHeaders == null) {
            httpHeaders = HashMap<String, String>()
        }

        /** Add required headers.  */
        httpHeaders!!.put("Accept", "application/json")
        return httpHeaders as Map<String, String>
    }

    override fun deliverResponse(response: T) {
        listener?.onResponse(response)
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
        try {
            val json = String(response.data, HttpHeaderParser.parseCharset(response.headers))
            return Response.success(GSON.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            return Response.error<T>(ParseError(e))
        } catch (e: JsonSyntaxException) {
            return Response.error<T>(ParseError(e))
        }
    }

    companion object {
        public val GSON: Gson = Gson()
    }
}
