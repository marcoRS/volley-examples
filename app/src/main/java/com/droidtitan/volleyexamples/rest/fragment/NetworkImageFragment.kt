package com.droidtitan.volleyexamples.rest.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.droidtitan.volleyexamples.rest.R
import com.droidtitan.volleyexamples.rest.util.ApiUrls
import com.droidtitan.volleyexamples.rest.util.setActionBarTitle
import com.droidtitan.volleyexamples.rest.util.withComponent
import javax.inject.Inject
import kotlin.properties.Delegates

public class NetworkImageFragment : Fragment() {

    var imageLoader: ImageLoader by Delegates.notNull()
        @Inject set

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View? {
        setActionBarTitle(R.string.networkimage_example)
        withComponent().inject(this)

        val root = inflater!!.inflate(R.layout.fragment_network_image, container, false)
        val map = root.findViewById(R.id.mapNetworkImageView) as NetworkImageView
        // map.setErrorImageResId(R.drawable.my_error_image);
        // map.setDefaultImageResId(R.drawable.my_default_image);
        map.setImageUrl(ApiUrls.getBerkeleyMapUrl(), imageLoader)

        return root
    }

    companion object {
        public val TAG: String = javaClass<NetworkImageFragment>().getName()
    }
}
