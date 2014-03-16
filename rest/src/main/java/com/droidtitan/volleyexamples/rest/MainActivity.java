package com.droidtitan.volleyexamples.rest;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.MenuItem;
import com.droidtitan.volleyexamples.rest.fragment.ExamplesListFragment;
import com.droidtitan.volleyexamples.rest.fragment.ExamplesListFragment.AttachFragmentEvent;
import com.droidtitan.volleyexamples.rest.util.Bus;

public class MainActivity extends FragmentActivity implements OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fm.addOnBackStackChangedListener(this);

        if (savedInstanceState == null) {
            fm.beginTransaction()
                    .add(R.id.container, new ExamplesListFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bus.unregister(this);
    }

    public void onEventMainThread(AttachFragmentEvent event) {
        FragmentManager fm = getSupportFragmentManager();
        String tag = event.getFragmentTag();

        if (fm.findFragmentByTag(tag) == null) {
            Fragment fragment = Fragment.instantiate(this, tag);
            fm.beginTransaction().replace(R.id.container, fragment, tag).addToBackStack(null).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        ActionBar actionBar = getActionBar();
        if (count > 0) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
