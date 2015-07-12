package com.droidtitan.volleyexamples.rest.util

import android.content.res.Resources
import com.android.volley.*
import com.droidtitan.volleyexamples.rest.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.apache.http.HttpStatus
import java.nio.charset.Charset
import java.util.HashMap


/**
 * Returns appropriate message which is to be displayed to the user
 * against the specified error object.
 * @param res Android resources for fetching a string.
 * @return A string representation of the error.
 */
public fun VolleyError.toString(res: Resources): String {
    if (this is TimeoutError) {
        return res.getString(R.string.generic_server_down)
    } else if (isServerProblem()) {
        return handleServerError(res)
    } else if (isNetworkProblem()) {
        return res.getString(R.string.no_data_connection)
    }
    return res.getString(R.string.generic_error)
}

private fun VolleyError.isNetworkProblem(): Boolean {
    return this is NetworkError || this is NoConnectionError
}

private fun VolleyError.isServerProblem(): Boolean {
    return this is ServerError || this is AuthFailureError
}

/**
 * Handles the server error, tries to determine whether to show a stock message or to
 * show a message retrieved from the server.
 * @return A string representation of the error.
 */
private fun VolleyError.handleServerError(res: Resources): String {

    if (networkResponse != null) {
        when (networkResponse.statusCode) {
            HttpStatus.SC_NOT_FOUND -> return res.getString(R.string.no_results_found)
            HttpStatus.SC_UNPROCESSABLE_ENTITY, HttpStatus.SC_UNAUTHORIZED -> {
                try {
                    // server might return error like this { "error": "Some error occurred" }
                    // Use "Gson" to parse the result
                    val result = Gson().fromJson<HashMap<String, String>>(String(networkResponse.data, Charset.forName("UTF-8")),
                            object : TypeToken<Map<String, String>>() {}.getType())

                    if (result != null && result.containsKey("error")) {
                        return result.get("error")
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

                // invalid request
                return getMessage() as String
            }

            else -> return res.getString(R.string.generic_server_down)
        }
    }
    return res.getString(R.string.generic_error)
}