package com.droidtitan.volley.util

import android.content.res.Resources
import com.android.volley.NetworkError
import com.android.volley.NetworkResponse
import com.droidtitan.volley.util.volley.toString
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers

class VolleyExtensionsTests {

  @Test fun testNetworkErrorMessageReturned() {
    val expected = "No connection. Please try again."
    val error = NetworkError(NetworkResponse(404, null, null, true))

    val res = mock<Resources> {
      on { getString(ArgumentMatchers.anyInt()) } doReturn expected
    }

    assertEquals(expected, error.toString(res))
  }
}