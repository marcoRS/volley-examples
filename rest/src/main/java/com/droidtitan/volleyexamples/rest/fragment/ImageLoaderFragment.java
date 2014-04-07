package com.droidtitan.volleyexamples.rest.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.droidtitan.volleyexamples.rest.R;
import com.droidtitan.volleyexamples.rest.VolleyApp;
import com.droidtitan.volleyexamples.rest.util.RestUtils;
import com.droidtitan.volleyexamples.rest.util.VolleyHelper;

import javax.inject.Inject;

public class ImageLoaderFragment extends Fragment {

    public static final String TAG = ImageLoaderFragment.class.getName();

    private VolleyApp app;
    @Inject ImageLoader imageLoader;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        app = (VolleyApp) activity.getApplication();
        app.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        getActivity().getActionBar().setTitle(R.string.image_loader_example);

        final ViewFlipper root = (ViewFlipper) inflater.inflate(R.layout.fragment_image_loader, container, false);
        final ImageView map = (ImageView) root.findViewById(R.id.mapImageView);

        imageLoader.get(RestUtils.SAN_FRAN_MAP_URL, new ImageListener() {
            @Override
            public void onResponse(ImageContainer imageContainer, boolean isImmediate) {
                Bitmap bitmap = imageContainer.getBitmap();
                if (bitmap != null) {
                    root.setDisplayedChild(1); // Set Layout with ImageView
                    map.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                root.setDisplayedChild(2); // Set error view.
                String errorMessage = VolleyHelper.getMessage(volleyError, app);
                Toast.makeText(app, errorMessage, Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
}
