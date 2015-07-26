package com.droidtitan.volley.util.volley;

import com.android.volley.VolleyError;

public interface Listener<T> {
    void onCompleted(VolleyError error, T response);
}
