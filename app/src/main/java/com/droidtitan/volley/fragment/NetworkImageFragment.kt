package com.droidtitan.volley.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.NetworkImageView
import com.droidtitan.volley.R
import com.droidtitan.volley.util.Api
import com.droidtitan.volley.util.setActionBarTitle
import com.droidtitan.volley.util.withComponent
import javax.inject.Inject

class NetworkImageFragment : Fragment() {
  @Inject lateinit var loader: ImageLoader

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
    setActionBarTitle(R.string.networkimage_example)
    withComponent().inject(this)

    val root = inflater.inflate(R.layout.fragment_network_image, container, false)
    val map = root.findViewById(R.id.mapNetworkImageView) as NetworkImageView
    // map.setErrorImageResId(R.drawable.my_error_image);
    // map.setDefaultImageResId(R.drawable.my_default_image);
    map.setImageUrl(Api.berkeleyMapUrl(), loader)

    return root
  }

  companion object {
    val TAG: String = NetworkImageFragment::class.java.name
  }
}
