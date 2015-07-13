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
        setActionBarTitle(R.string.json_request_example)
        withComponent().inject(this)
        return inflater!!.inflate(R.layout.fragment_gson_request, container, false)
    }

    override fun onViewCreated(view: View?, state: Bundle?) {
        super.onViewCreated(view, state)
        flipper.findViewById(R.id.retryButton).setOnClickListener { getAirQuality() }
        if (response == null) getAirQuality()
    }

    private fun getAirQuality() {
        flipper.setDisplayedChild(0)

        val URL = Api.getAirQualityUrl()
        val listener = Listener<AirQualityResponse> { r -> Bus.post(AirQualityEvent(r, null)) }
        val errListener = ErrorListener { e -> Bus.post(AirQualityEvent(null, e)) }

        val request = GsonRequest(URL, javaClass<AirQualityResponse>(), listener, errListener)
        request.setTag(AIR_QUALITY).setShouldCache(false)
        queue.add(request)
    }

    public fun onEventMainThread(event: AirQualityEvent) {
        val error = event.error
        response = event.response

        if (error == null) {
            flipper.setDisplayedChild(1)
            temperature.setText(response!!.getTemperature() + CELCIUS)

            val category = response!!.getAirQualityCategory()
            airQuality.setText(category!!.firstToUpperCase())
        } else {
            flipper.setDisplayedChild(2)
            showSnackbar(error.toString(getResources()))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        queue.cancelAll(AIR_QUALITY)
    }

    class AirQualityEvent(var response: AirQualityResponse?, var error: VolleyError?)

    companion object {
        public val TAG: String = javaClass<GsonRequestFragment>().getName()
        val AIR_QUALITY: String = "AirQualityTag"
        val CELCIUS: String = " \u2103"
    }
}