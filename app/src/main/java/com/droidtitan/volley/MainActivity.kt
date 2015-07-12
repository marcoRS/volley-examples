package com.droidtitan.volley

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.droidtitan.volley.fragment.ExamplesListFragment
import com.droidtitan.volley.fragment.ExamplesListFragment.AttachFragmentEvent
import com.droidtitan.volley.util.Bus

public class MainActivity : AppCompatActivity() {

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_main)

        val fm = getSupportFragmentManager()
        fm.addOnBackStackChangedListener { showHomeButton(fm.getBackStackEntryCount() > 0) }

        if (state == null) {
            fm.beginTransaction().add(R.id.container, ExamplesListFragment()).commit()
        }
    }

    fun showHomeButton(enable: Boolean) {
        val ab = getSupportActionBar()
        ab.setHomeButtonEnabled(enable)
        ab.setDisplayHomeAsUpEnabled(enable)
    }

    override fun onResume() {
        super.onResume()
        Bus.register(this)
    }

    override fun onPause() {
        super.onPause()
        Bus.unregister(this)
    }

    public fun onEventMainThread(event: AttachFragmentEvent) {
        val fm = getSupportFragmentManager()
        val tag = event.fragmentTag

        if (fm.findFragmentByTag(tag) == null) {
            val f = Fragment.instantiate(this, tag)
            fm.beginTransaction().replace(R.id.container, f, tag).addToBackStack(null).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        if (id == android.R.id.home) {
            getSupportFragmentManager().popBackStack()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}