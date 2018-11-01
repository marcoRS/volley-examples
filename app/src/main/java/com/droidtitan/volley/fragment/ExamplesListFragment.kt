package com.droidtitan.volley.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.droidtitan.volley.R
import com.droidtitan.volley.util.Bus
import com.droidtitan.volley.util.setActionBarTitle

class ExamplesListFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
    setActionBarTitle(R.string.app_name)

    val listView = ListView(activity)
    val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, getExamples())

    listView.adapter = adapter
    listView.setOnItemClickListener { _, _, position, _ ->
      val t = listOf(GsonRequestFragment.TAG, NetworkImageFragment.TAG, ImageLoaderFragment.TAG)[position]
      Bus.post(AttachFragmentEvent(t))
    }

    return listView
  }

  private fun getExamples(): List<String> = listOf(getString(R.string.json_request_example),
    getString(R.string.networkimage_example),
    getString(R.string.image_loader_example))

  class AttachFragmentEvent(val fragmentTag: String)
}
