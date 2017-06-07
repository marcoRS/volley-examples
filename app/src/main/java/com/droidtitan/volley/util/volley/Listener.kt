package com.droidtitan.volley.util.volley

import com.android.volley.VolleyError

interface Listener<in T> {
  fun onCompleted(volleyError: VolleyError?, apiResponse: T?)
}
