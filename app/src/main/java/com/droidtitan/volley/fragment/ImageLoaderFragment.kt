package com.droidtitan.volley.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ViewFlipper
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageLoader.ImageContainer
import com.android.volley.toolbox.ImageLoader.ImageListener
import com.droidtitan.volley.R
import com.droidtitan.volley.util.Api
import com.droidtitan.volley.util.setActionBarTitle
import com.droidtitan.volley.util.showSnackbar
import com.droidtitan.volley.util.volley.toString
import com.droidtitan.volley.util.withComponent
import javax.inject.Inject

class ImageLoaderFragment : Fragment() {

  @Inject lateinit var loader: ImageLoader

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
    setActionBarTitle(R.string.image_loader_example)
    withComponent().inject(this)

    val root = inflater.inflate(R.layout.fragment_image_loader, container, false) as ViewFlipper
    val map = root.findViewById(R.id.mapImageView) as ImageView

    loader.get(Api.sfMapUrl(), object : ImageListener {
      override fun onResponse(container: ImageContainer, isImmediate: Boolean) {
        container.bitmap?.let {
          root.displayedChild = 1
          map.setImageBitmap(it)
        }
      }

      override fun onErrorResponse(error: VolleyError) {
        root.displayedChild = 2
        showSnackbar(error.toString(resources))
      }
    })

    return root
  }

  companion object {
    val TAG: String = ImageLoaderFragment::class.java.name
  }
}