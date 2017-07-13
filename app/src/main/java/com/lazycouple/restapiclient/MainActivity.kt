package com.lazycouple.restapiclient

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.amuyu.logger.Logger
import com.lazycouple.restapiclient.databinding.ActivityMainBinding
import com.lazycouple.restapiclient.ui.RequestHistoryFragment
import com.lazycouple.restapiclient.ui.RequestMainFragment
import com.lazycouple.restapiclient.ui.RestRequestFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("")
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding!!.appBar.toolbar)

        binding!!.appBar.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        val toggle = ActionBarDrawerToggle(
                this, binding!!.drawerLayout, binding!!.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding!!.drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        binding!!.navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null)
            selectItem(R.id.nav_request)
    }

    override fun onBackPressed() {
        if (binding!!.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (processBackPressed())
                super.onBackPressed()
        }
    }

    private fun processBackPressed(): Boolean {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentById(R.id.content_frame)

        if (fragment is RestRequestFragment) {
            return fragment.onBackPressed()
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //        getMenuInflater().inflate(R.menu.main, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_request) {
            // Handle the camera action
        } else if (id == R.id.nav_history) {

        }

        selectItem(id)


        binding!!.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * main 화면 변경
     * @param id
     */
    private fun selectItem(id: Int) {
        Logger.d("selectItem#id:" + id)
        var fragment: Fragment? = null

        when (id) {
            R.id.nav_request -> fragment = RequestMainFragment.newInstance()
            R.id.nav_history -> fragment = RequestHistoryFragment.newInstance()
        }

        if (fragment != null) {
            switchFragment(fragment)
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment)
        fragmentTransaction.commit()
    }


    fun loadHistoryFragment(id: String) {
        val fragment = RestRequestFragment.newInstance()
        (fragment as RestRequestFragment).setId(id)
        switchFragment(fragment)
    }


}
