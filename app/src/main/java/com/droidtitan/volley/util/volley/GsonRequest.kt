package com.droidtitan.volley.util.volley

import com.android.volley.*
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.toolbox.HttpHeaderParser
import com.google.gson.Gson

/** Volley adapter for JSON requests that will be parsed into Java objects by Gson. */
class GsonRequest<T> : Request<T> {
  private val clazz: Class<T>
  /** headerz is lazily initialized when using Delegates.lazy. */
  private val headerz: MutableMap<String, String> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { hashMapOf("Accept" to "application/json") }
  private val listener: Listener<T>

  /**
   * Make a GET request and return a parsed object from JSON.
   * @param url     URL of the request to make
   * @param clazz   Relevant class object, for Gson's reflection
   */
  constructor(url: String, clazz: Class<T>, listener: Listener<T>, errorListener: ErrorListener) :
    super(Request.Method.GET, url, errorListener) {
    this.clazz = clazz
    this.listener = listener
  }

  /**
   * Make a specified Http Request and return a parsed object from JSON.
   * @param method        The Http Method type as specified by Request.Method
   * @param url           URL of the request to make
   * @param clazz         Relevant class object, for Gson's reflection
   * @param listener      Callback interface for delivering parsed responses
   * @param errorListener Callback interface for delivering error responses
   */
  constructor(method: Int, url: String, clazz: Class<T>, listener: Listener<T>, errorListener: ErrorListener) :
    super(method, url, errorListener) {
    this.clazz = clazz
    this.listener = listener
  }

  @Throws(AuthFailureError::class)
  override fun getHeaders(): Map<String, String> = headerz

  override fun deliverResponse(response: T) = listener.onResponse(response)

  override fun parseNetworkResponse(response: NetworkResponse): Response<T> {
    try {
      val json = java.lang.String(response.data, HttpHeaderParser.parseCharset(response.headers)) as String
      return Response.success(GSON.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response))
    } catch (e: Exception) {
      return Response.error<T>(ParseError(e))
    }
  }

  companion object {
    val GSON: Gson by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { Gson() }
  }
}