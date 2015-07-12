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
import com.droidtitan.volleyexamples.rest.util.GsonRequest
import javax.inject.Inject
import kotlin.properties.Delegates

public class GsonRequestFragment : Fragment() {
    var flipper: ViewFlipper by Delegates.notNull()
    var airQuality: TextView by Delegates.notNull()
    var temperature: TextView by Delegates.notNull()

    var response: AirQualityResponse? = null
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedState: Bundle?): View? {
        setActionBarTitle(R.string.json_request_example)
        withComponent().inject(this)

        flipper = inflater!!.inflate(R.layout.fragment_gson_request, container, false) as ViewFlipper

        airQuality = flipper.findViewById(R.id.qualityTextView) as TextView
        temperature = flipper.findViewById(R.id.temperatureTextView) as TextView
        flipper.findViewById(R.id.retryButton).setOnClickListener { getAirQuality() }

        if (response == null) {
            getAirQuality()
        }

        return flipper
    }

    private fun getAirQuality() {
        flipper.setDisplayedChild(0)

        val URL = ApiUrls.getAirQualityUrl()
        val listener = Listener<AirQualityResponse> { r -> Bus.post(AirQualityEvent(r, null)) }
        val errListener = ErrorListener { e -> Bus.post(AirQualityEvent(null, e)) }

        val request = GsonRequest(URL, javaClass<AirQualityResponse>(), null, listener, errListener)
        request.setTag(AIR_QUALITY).setShouldCache(false)
        queue.add(request)
    }

    override fun onDestroy() {
        super.onDestroy()
        queue.cancelAll(AIR_QUALITY)
    }

    public fun onEventMainThread(event: AirQualityEvent) {
        val error = event.volleyError
        response = event.response

        if (error == null) {
            flipper.setDisplayedChild(1)
            temperature.setText(response!!.getTemperature() + " \u2103")

            val category = response!!.getAirQualityCategory()
            airQuality.setText(category!!.firstToUpperCase())
        } else {
            flipper.setDisplayedChild(2)
            toast(error.toString(getResources()))
        }
    }

    public class AirQualityEvent(response: AirQualityResponse?, volleyError: VolleyError?)
    : HttpResponseEvent<AirQualityResponse>(response, volleyError)

    companion object {
        public val TAG: String = javaClass<GsonRequestFragment>().getName()
        public val AIR_QUALITY: String = "AirQualityTag"
    }
}
