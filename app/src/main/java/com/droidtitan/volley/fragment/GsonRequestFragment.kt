package com.droidtitan.volley.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.droidtitan.volley.R
import com.droidtitan.volley.model.air.AirQualityResponse
import com.droidtitan.volley.util.*
import com.droidtitan.volley.util.volley.*
import org.koin.android.ext.android.inject

class GsonRequestFragment : Fragment() {
  val flipper: ViewFlipper by bindView(R.id.gsonFlipper)
  val airQuality: TextView by bindView(R.id.qualityTextView)
  val temperature: TextView by bindView(R.id.temperatureTextView)

  var response: AirQualityResponse? = null
  val queue: RequestQueue by inject()

  override fun onResume() {
    super.onResume()
    Bus.register(this)
  }

  override fun onPause() {
    super.onPause()
    Bus.unregister(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
    /** Extension functions add class functionality without inheriting they are resolved statically */
    setActionBarTitle(R.string.json_request_example)

    /** !! operator will return a non-null value, otherwise throws a NPE */
    return inflater.inflate(R.layout.fragment_gson_request, container, false)
  }

  override fun onViewCreated(view: View, state: Bundle?) {
    super.onViewCreated(view, state)
    /** Any method that accepts a one method interface can use this { } convention. */
    flipper.findViewById<View>(R.id.retryButton).setOnClickListener { getAirQuality() }
    response ?: getAirQuality()
  }

  private fun getAirQuality() {
    flipper.displayedChild = 0

    val listener = object : Listener<AirQualityResponse> {
      override fun onCompleted(volleyError: VolleyError?, apiResponse: AirQualityResponse?) {
        volleyError?.let { Bus.post(AirQualityEvent(error = volleyError)) }
        apiResponse?.let { Bus.post(AirQualityEvent(response = apiResponse)) }
      }

    }

    queue.add(listener, Api.airQualityUrl(), { it.dontCache().withTag(AIR_QUALITY) })
  }

  fun onEventMainThread(event: AirQualityEvent) {
    val error = event.error
    response = event.response

    if (error != null) {
      /** Smart cast only works if val is used. */
      showSnackbar(error.toString(resources))
    } else {
      temperature.text = response!!.getTemperature() + CELCIUS
      /**
       * safe call operator ? calls the method category() if not null else null is returned.
       * if null is returned the 2nd ?: ensures "" is returned
       * */
      airQuality.text = response!!.category()?.firstToUpperCase() ?: ""
    }

    /** There is no ternary operator equivalent to Java, if else can be inlined however.*/
    flipper.displayedChild = if (error != null) 2 else 1
  }

  override fun onDestroy() {
    super.onDestroy()
    queue.cancelAll(AIR_QUALITY)
  }

  /** Has default values in constructor. Getters/setters are automatically generated */
  class AirQualityEvent(val response: AirQualityResponse? = null, val error: VolleyError? = null)

  /** Companion objects are used to create static properties. */
  companion object {
    @JvmField
    val TAG: String = GsonRequestFragment::class.java.name
    val AIR_QUALITY: String = "AirQualityTag"
    val CELCIUS: String = " \u2103"
  }
}