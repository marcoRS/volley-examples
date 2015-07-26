package com.droidtitan.volley.util

import com.android.volley.VolleyError

inline fun <T> T.apply(f: T.() -> Any): T {
    this.f()
    return this
}