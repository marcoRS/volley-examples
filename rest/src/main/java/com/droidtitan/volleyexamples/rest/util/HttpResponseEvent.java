package com.droidtitan.volleyexamples.rest.util;

import com.android.volley.VolleyError;

public class HttpResponseEvent<T> {
    private T response;
    private VolleyError volleyError;

    public T getResponse() {
        return response;
    }

    public HttpResponseEvent<T> setResponse(T response) {
        this.response = response;
        return this;
    }

    public VolleyError getVolleyError() {
        return volleyError;
    }

    public HttpResponseEvent<T> setVolleyError(VolleyError error) {
        volleyError = error;
        return this;
    }
}
