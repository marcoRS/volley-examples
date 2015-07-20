package com.droidtitan.volley.util

inline fun <T> T.apply(f: T.() -> Any): T {
    this.f()
    return this
}