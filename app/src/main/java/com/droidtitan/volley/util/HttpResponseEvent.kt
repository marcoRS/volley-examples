package com.droidtitan.volley.util

import com.android.volley.VolleyError

public open class HttpResponseEvent<T>(var response: T?, var volleyError: VolleyError?) {
}
