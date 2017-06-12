package com.lazycouple.restapiclient;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.databinding.ActivityMainBinding;
import com.lazycouple.restapiclient.ui.RequestHistoryFragment;
import com.lazycouple.restapiclient.ui.RestRequestFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.appBar.toolbar);

        binding.appBar.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null)
            selectItem(R.id.nav_camera);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if(processBackPressed())
                super.onBackPressed();
        }
    }

    private boolean processBackPressed()
    {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_frame);

        if(fragment instanceof RestRequestFragment)
        {
            return ((RestRequestFragment) fragment).onBackPressed();
        }

        return true;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        selectItem(id);


        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * main 화면 변경
     * @param id
     */
    private void selectItem(int id)
    {
        Logger.d("selectItem#id:"+id);
        Fragment fragment = null;

        switch (id)
        {
            case R.id.nav_camera:
                fragment = RestRequestFragment.newInstance();
                break;
            case R.id.nav_gallery:
                fragment = RequestHistoryFragment.newInstance();
                break;
        }

        if(fragment != null)
        {
            switchFragment(fragment);
        }
    }

    private void switchFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }


    public void loadHistoryFragment(String historyName)
    {
        Fragment fragment = RestRequestFragment.newInstance();
        ((RestRequestFragment)fragment).setHistoryName(historyName);
        switchFragment(fragment);
    }


}
