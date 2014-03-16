package com.droidtitan.volleyexamples.rest.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.droidtitan.volleyexamples.rest.R;
import com.droidtitan.volleyexamples.rest.VolleyApp;
import com.droidtitan.volleyexamples.rest.model.air.AirQuality;
import com.droidtitan.volleyexamples.rest.model.air.AirQualityResponse;
import com.droidtitan.volleyexamples.rest.util.Bus;
import com.droidtitan.volleyexamples.rest.util.GsonRequest;
import com.droidtitan.volleyexamples.rest.util.HttpResponseEvent;
import com.droidtitan.volleyexamples.rest.util.RestUtils;
import com.droidtitan.volleyexamples.rest.util.VolleyHelper;

import javax.inject.Inject;

public class RequestFragment extends Fragment implements OnClickListener {

    public static final String TAG = RequestFragment.class.getName();

    public static final String DOWNLOAD_TAG = "AirQualityTag";

    private TextView airQualityTextView;
    private TextView tempartureTextView;
    private ViewFlipper rootView;

    private VolleyApp app;

    private AirQualityResponse response;
    @Inject RequestQueue requestQueue;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        app = (VolleyApp) activity.getApplication();
        app.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Bus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Bus.unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        getActivity().getActionBar().setTitle(R.string.json_request_example);

        rootView = (ViewFlipper) inflater.inflate(R.layout.fragment_request, container, false);

        airQualityTextView = (TextView) rootView.findViewById(R.id.qualityTextView);;
        tempartureTextView = (TextView) rootView.findViewById(R.id.temperatureTextView);
        rootView.findViewById(R.id.retryButton).setOnClickListener(this);

        if (response == null) {
            startRequest();
        }

        return rootView;
    }

    private void startRequest() {
        final String url = RestUtils.getAirQualityUrl();
        final GsonRequest<AirQualityResponse> request = new GsonRequest<AirQualityResponse>(Method.GET,
                url, AirQualityResponse.class, null,
                new Listener<AirQualityResponse>() {
                    @Override
                    public void onResponse(AirQualityResponse response) {
                        Bus.postEvent(new AirQualityEvent().setResponse(response));
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Bus.postEvent(new AirQualityEvent().setVolleyError(error));
            }
        }
        );

        request.setTag(DOWNLOAD_TAG);
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll(DOWNLOAD_TAG);
    }

    public void onEventMainThread(AirQualityEvent event) {
        VolleyError error = event.getVolleyError();
        response = event.getResponse();

        if (error == null) {
            rootView.setDisplayedChild(1);
            tempartureTextView.setText(response.getWeather().getTemperature() + " \u2103");

            AirQuality quality = response.getAirQuality();
            String category = quality.getCategory();
            airQualityTextView.setText(category.substring(0, 1).toUpperCase() + category.substring(1));
        } else {
            rootView.setDisplayedChild(2);
            Toast.makeText(app, VolleyHelper.getMessage(error, app), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        startRequest();
    }

    public class AirQualityEvent extends HttpResponseEvent<AirQualityResponse> {
    }
}
