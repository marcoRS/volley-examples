package com.droidtitan.volleyexamples.rest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.droidtitan.volleyexamples.rest.R;
import com.droidtitan.volleyexamples.rest.util.Bus;

import java.util.ArrayList;

public class ExamplesListFragment extends Fragment implements OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView listView = new ListView(getActivity());

        ArrayList<String> examples = new ArrayList<String>();
        examples.add(getString(R.string.json_request_example));
        examples.add(getString(R.string.networkimage_example));
        examples.add(getString(R.string.image_loader_example));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_example, examples);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return listView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Bus.postEvent(new AttachFragmentEvent(RequestFragment.TAG));
        } else if (position == 1) {
            Bus.postEvent(new AttachFragmentEvent(NetworkImageFragment.TAG));
        } else {
            Bus.postEvent(new AttachFragmentEvent(ImageLoaderFragment.TAG));
        }
    }

    public static class AttachFragmentEvent {
        private String fragmentTag;

        public AttachFragmentEvent(String fragmentTag) {
            this.fragmentTag = fragmentTag;
        }

        public String getFragmentTag() {
            return fragmentTag;
        }
    }
}
