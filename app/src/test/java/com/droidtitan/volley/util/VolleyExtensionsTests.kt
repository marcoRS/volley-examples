package com.droidtitan.volley.util

import android.content.res.Resources
import com.android.volley.NetworkError
import com.android.volley.NetworkResponse
import com.droidtitan.volley.util.volley.toString
import org.junit.Test
import org.mockito.Matchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

public class VolleyExtensionsTests {

    @Test fun testNetworkErrorMessageReturned() {
        val expected = "No connection. Please try again."
        val error = NetworkError(NetworkResponse(404, null, null, true))

        val res = Mockito.mock(javaClass<Resources>())
        `when`(res.getString(anyInt())).thenReturn(expected)

        assertEquals(expected, error.toString(res))
    }
}