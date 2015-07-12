package com.droidtitan.volley.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.droidtitan.volley.R
import com.droidtitan.volley.util.Bus
import com.droidtitan.volley.util.setActionBarTitle

public class ExamplesListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View? {
        setActionBarTitle(R.string.app_name)

        val listView = ListView(getActivity())
        val adapter = ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, getExamples())

        listView.setAdapter(adapter)
        listView.setOnItemClickListener { a, v, p, i ->
            val t = listOf(GsonRequestFragment.TAG, NetworkImageFragment.TAG, ImageLoaderFragment.TAG).get(p)
            Bus.post(AttachFragmentEvent(t))
        }

        return listView
    }

    fun getExamples(): List<String> = listOf(getString(R.string.json_request_example),
            getString(R.string.networkimage_example),
            getString(R.string.image_loader_example))

    public class AttachFragmentEvent(val fragmentTag: String)
}
