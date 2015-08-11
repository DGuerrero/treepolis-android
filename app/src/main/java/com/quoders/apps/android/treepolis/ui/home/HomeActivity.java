package com.quoders.apps.android.treepolis.ui.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.parse.ParseUser;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.ui.HomeMapFragment;
import com.quoders.apps.android.treepolis.ui.maps.GoogleMapsMng;
import com.quoders.apps.android.treepolis.ui.maps.LocationMng;
import com.quoders.apps.android.treepolis.ui.welcome.WelcomeActivity;

import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
                    HomeMapFragment.OnFragmentInteractionListener {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private DrawerLayout mDrawerLayout;
    private View content;

    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBar mActionBar;

    private GoogleMapsMng mMapMng;
    private LocationMng mLocationMng;

    private HomePresenter mPresenter;

    @OnClick(R.id.buttonCheckTree)
    public void checkTreeButtonClick(View view) {
        mPresenter.onCheckTree();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTitle = getTitle();

        content = findViewById(R.id.container);

        setupDrawerLayout();

        initToolbar();

        setUserData();

        setMapFragment();

        setLocationManager();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mLocationMng != null) {
            mLocationMng.startLocationService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mLocationMng != null) {
            mLocationMng.stopLocationService();
        }
    }

    private void setLocationManager() {
        mLocationMng = new LocationMng(this, true, mMapMng);
    }


    private void setMapFragment() {

        mMapMng = new GoogleMapsMng();

        // Obtain the MapFragment and set the async listener to be notified when the map is ready.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(mMapMng);
    }

    private void setUserData() {

        ParseUser currentUser = ParseUser.getCurrentUser();

        if(currentUser != null) {
            ((TextView)findViewById(R.id.textViewDrawerHeaderEmail)).setText(currentUser.getEmail());
            ((TextView)findViewById(R.id.textViewDrawerHeaderUsername)).setText(currentUser.getUsername());
        }
    }


    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void setupDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.drawer_logout) {
                    performUserLogout();
                }
                else {
                    Snackbar.make(content, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                    menuItem.setChecked(true);
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                mActionBar.setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mActionBar.setTitle(mTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void performUserLogout() {

        ParseUser.logOut();
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

        // update the main content by replacing fragments
        /*
        FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
