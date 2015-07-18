package idv.hangermo.materialex;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import idv.hangermo.materialex.dateplace.DatePlace;
import idv.hangermo.materialex.hotnews.HotNews;
import idv.hangermo.materialex.lastest.Lastest;
import idv.hangermo.materialex.lazyroute.LazyRoute;


public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawerLayout();
        initFAB();
    }

    private void initFAB() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(frameLayout, "FAB Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView view = (NavigationView) findViewById(R.id.navigationView);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(frameLayout, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.drawer_lastest:
                        String valueLastest = "Lastest test OK";
                        Intent intentLastest = new Intent();
                        intentLastest.setClass(MainActivity.this, Lastest.class);
                        Bundle bundleLastest = new Bundle();
                        bundleLastest.putString("msg", valueLastest);
                        intentLastest.putExtras(bundleLastest);
                        startActivity(intentLastest);
                        break;
                    case R.id.drawer_hotnews:
                        String valueHotnews = "Hotnews test OK";
                        Intent intentHotnews = new Intent();
                        intentHotnews.setClass(MainActivity.this, HotNews.class);
                        Bundle bundleHotnews = new Bundle();
                        bundleHotnews.putString("msg", valueHotnews);
                        intentHotnews.putExtras(bundleHotnews);
                        startActivity(intentHotnews);
                        break;

                    case R.id.drawer_lazyroute:
                        String valueLazyRoute = "LazyRoute test OK";
                        Intent intentLayRoute = new Intent();
                        intentLayRoute.setClass(MainActivity.this, LazyRoute.class);
                        Bundle bundleLazyRoute = new Bundle();
                        bundleLazyRoute.putString("msg", valueLazyRoute);
                        intentLayRoute.putExtras(bundleLazyRoute);
                        startActivity(intentLayRoute);
                        break;

                    case R.id.drawer_dateplace:
                        String valueDateplace = "Dateplace test OK";
                        Intent intentDateplace = new Intent();
                        intentDateplace.setClass(MainActivity.this, DatePlace.class);
                        Bundle bundleDateplace = new Bundle();
                        bundleDateplace.putString("msg", valueDateplace);
                        intentDateplace.putExtras(bundleDateplace);
                        startActivity(intentDateplace);
                        break;
                    case R.id.drawer_settings:

                        break;
                }



                drawerLayout.closeDrawers();
                return true;
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                //Nothing here
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //Nothing here
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
