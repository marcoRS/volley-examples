package com.droidtitan.volley.util

import com.android.volley.NetworkError
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.droidtitan.volley.App
import com.droidtitan.volley.BuildConfig
import com.droidtitan.volley.model.air.AirQualityResponse
import com.droidtitan.volley.util.volley.GsonRequest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.properties.Delegates

@Config(sdk = intArrayOf(21), application = App::class, constants = BuildConfig::class)
@RunWith(RobolectricTestRunner::class) class GsonRequestTests {

  var request: GsonRequest<AirQualityResponse> by Delegates.notNull()
  var errorListener: ErrorListener by Delegates.notNull()

  @Before fun setUp() {
    val url = "http://responseUrl.com"
    val clazz = AirQualityResponse::class.java

    val listener = mock(Listener::class.java) as Listener<AirQualityResponse>
    errorListener = mock(ErrorListener::class.java)
    request = GsonRequest(url, clazz, listener, errorListener)
  }

  @Test fun testErrorListenerResponseCalled() {
    val error = NetworkError()
    request.deliverError(error)
    verify(errorListener, times(1)).onErrorResponse(error)
  }

  @Test fun testAcceptHeaderIsAdded() {
    assertEquals("application/json", request.headers["Accept"])
  }
}