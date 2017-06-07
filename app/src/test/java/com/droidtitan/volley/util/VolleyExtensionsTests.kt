package com.droidtitan.volley.util

import android.content.res.Resources
import com.android.volley.NetworkError
import com.android.volley.NetworkResponse
import com.droidtitan.volley.util.volley.toString
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Matchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class VolleyExtensionsTests {

  @Test fun testNetworkErrorMessageReturned() {
    val expected = "No connection. Please try again."
    val error = NetworkError(NetworkResponse(404, null, null, true))

    val res = Mockito.mock(Resources::class.java)
    `when`(res.getString(anyInt())).thenReturn(expected)

    assertEquals(expected, error.toString(res))
  }
}