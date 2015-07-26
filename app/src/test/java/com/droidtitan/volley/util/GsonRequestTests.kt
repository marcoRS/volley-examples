package com.droidtitan.volley.util

import com.android.volley.NetworkError
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.droidtitan.volley.App
import com.droidtitan.volley.BuildConfig
import com.droidtitan.volley.model.air.AirQualityResponse
import com.droidtitan.volley.util.volley.GsonRequest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.properties.Delegates
import kotlin.test.assertEquals

@Config(sdk = intArrayOf(21), application = App::class, constants = BuildConfig::class)
@RunWith(RobolectricGradleTestRunner::class)
public class GsonRequestTests {

    var request: GsonRequest<AirQualityResponse> by Delegates.notNull()
    var errorListener: ErrorListener by Delegates.notNull()

    @Before fun setUp() {
        val url = "http://responseUrl.com"
        val clazz = javaClass<AirQualityResponse>()

        val listener = mock(javaClass<Listener<AirQualityResponse>>())
        errorListener = mock(javaClass<ErrorListener>())
        request = GsonRequest(url, clazz, listener, errorListener)
    }

    @Test fun testErrorListenerResponseCalled() {
        val error = NetworkError()
        request.deliverError(error)
        verify(errorListener, times(1)).onErrorResponse(error)
    }

    @Test fun testAcceptHeaderisAdded() {
        assertEquals("application/json", request.getHeaders().get("Accept"))
    }
}