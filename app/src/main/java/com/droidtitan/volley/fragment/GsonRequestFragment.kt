package com.droidtitan.volley.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewFlipper
import com.android.volley.RequestQueue
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.droidtitan.volley.R
import com.droidtitan.volley.model.air.AirQualityResponse
import com.droidtitan.volley.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

public class GsonRequestFragment : Fragment() {
    val flipper: ViewFlipper by bindView(R.id.gsonFlipper)
    val airQuality: TextView by bindView(R.id.qualityTextView)
    val temperature: TextView by bindView(R.id.temperatureTextView)

    var response: AirQualityResponse? = null
    /** Dagger injection is done through method injection. The delegate allows to defer assignment*/
    var queue: RequestQueue by Delegates.notNull()
        @Inject set

    override fun onResume() {
        super.onResume()
        Bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        Bus.unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View? {
        /** Extension functions add class functionality without inheriting they are resolved statically */
        setActionBarTitle(R.string.json_request_example)
        withComponent().inject(this)

        /** !! operator will return a non-null value, otherwise throws a NPE */
        return inflater!!.inflate(R.layout.fragment_gson_request, container, false)
    }

    override fun onViewCreated(view: View?, state: Bundle?) {
        super.onViewCreated(view, state)
        /** Any method that accepts a one method interface can use this { } convention. */
        flipper.findViewById(R.id.retryButton).setOnClickListener { getAirQuality() }
        if (response == null) getAirQuality()
    }

    private fun getAirQuality() {
        flipper.setDisplayedChild(0)

        val url = Api.airQualityUrl()
        /** Any interface with one method can use this { } convention. (SAM conversion )*/
        val listener = Listener<AirQualityResponse> { r -> Bus.post(AirQualityEvent(r)) }
        val errorListener = ErrorListener { e -> Bus.post(AirQualityEvent(null, e)) }

        val request = GsonRequest(url, javaClass<AirQualityResponse>(), listener, errorListener)
        request.setTag(AIR_QUALITY).setShouldCache(false)
        queue.add(request)
    }

    public fun onEventMainThread(event: AirQualityEvent) {
        val error = event.error
        response = event.response

        if (error != null) {
            /** Smart cast only works if val is used. */
            showSnackbar(error.toString(getResources()))
        } else {
            temperature.setText(response!!.getTemperature() + CELCIUS)
            /**
             * safe call operator ? calls the method category() if not null else null is returned.
             * if null is returned the 2nd ?: ensures "" is returned
             * */
            airQuality.setText(response!!.category()?.firstToUpperCase() ?: "")
        }

        /** There is no ternary operator equivalent to Java, if else can be inlined however.*/
        flipper.setDisplayedChild(if (error != null) 2 else 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        queue.cancelAll(AIR_QUALITY)
    }

    /** Has default values in constructor. Getters/setters are automatically generated */
    class AirQualityEvent(val response: AirQualityResponse? = null, val error: VolleyError? = null)

    /** Companion objects are used to create static properties. */
    companion object {
        public val TAG: String = javaClass<GsonRequestFragment>().getName()
        val AIR_QUALITY: String = "AirQualityTag"
        val CELCIUS: String = " \u2103"
    }
}