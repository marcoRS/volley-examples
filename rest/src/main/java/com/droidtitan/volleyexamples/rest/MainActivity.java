package com.droidtitan.volleyexamples.rest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import com.droidtitan.volleyexamples.rest.fragment.ExamplesListFragment;
import com.droidtitan.volleyexamples.rest.fragment.ExamplesListFragment.AttachFragmentEvent;
import com.droidtitan.volleyexamples.rest.util.Bus;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
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
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
