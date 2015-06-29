package com.droidtitan.volleyexamples.rest.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.droidtitan.volleyexamples.rest.App;
import com.droidtitan.volleyexamples.rest.R;
import com.droidtitan.volleyexamples.rest.util.RestUtils;

import javax.inject.Inject;

public class NetworkImageFragment extends Fragment {

    public static final String TAG = NetworkImageFragment.class.getName();

    @Inject
    ImageLoader imageLoader;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        App.from(getActivity()).appComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        getActivity().getActionBar().setTitle(R.string.networkimage_example);

        View root = inflater.inflate(R.layout.fragment_network_image, container, false);

        NetworkImageView imageView = (NetworkImageView) root.findViewById(R.id.mapNetworkImageView);
        // imageView.setErrorImageResId(R.drawable.my_error_image);
        // imageView.setDefaultImageResId(R.drawable.my_default_image);
        imageView.setImageUrl(RestUtils.getBerkeleyMapUrl(), imageLoader);

        return root;
    }
}
