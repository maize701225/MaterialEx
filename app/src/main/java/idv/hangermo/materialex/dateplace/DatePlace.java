package idv.hangermo.materialex.dateplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import idv.hangermo.materialex.R;
import idv.hangermo.materialex.hotnews.HotNews;
import idv.hangermo.materialex.lastest.Lastest;
import idv.hangermo.materialex.lazyroute.LazyRoute;


/**
 * Created by user on 2015/6/24.
 */
public class DatePlace extends AppCompatActivity {
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton fab;

    private TextView textView4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dateplace_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawerLayout();
        initFAB();

        textView4=(TextView)findViewById(R.id.textView4);


        Bundle bundle=getIntent().getExtras();
        String value=bundle.getString("msg");
        textView4.setText(value);

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

                switch (menuItem.getItemId()) {
                    case R.id.drawer_lastest:
                        String valueLastest = "Lastest test OK";
                        Intent intentLastest = new Intent();
                        intentLastest.setClass(DatePlace.this, Lastest.class);
                        Bundle bundleLastest = new Bundle();
                        bundleLastest.putString("msg", valueLastest);
                        intentLastest.putExtras(bundleLastest);
                        startActivity(intentLastest);
                        finish();
                        break;
                    case R.id.drawer_hotnews:
                        String valueHotnews = "Hotnews test OK";
                        Intent intentHotnews = new Intent();
                        intentHotnews.setClass(DatePlace.this, HotNews.class);
                        Bundle bundleHotnews = new Bundle();
                        bundleHotnews.putString("msg", valueHotnews);
                        intentHotnews.putExtras(bundleHotnews);
                        startActivity(intentHotnews);
                        finish();
                        break;

                    case R.id.drawer_lazyroute:
                        String valueLazyRoute = "LazyRoute test OK";
                        Intent intentLayRoute = new Intent();
                        intentLayRoute.setClass(DatePlace.this, LazyRoute.class);
                        Bundle bundleLazyRoute = new Bundle();
                        bundleLazyRoute.putString("msg", valueLazyRoute);
                        intentLayRoute.putExtras(bundleLazyRoute);
                        startActivity(intentLayRoute);
                        finish();
                        break;

                    case R.id.drawer_settings:

                        break;
                }
                menuItem.setChecked(true);
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
    public void onClick(View view){
        finish();
    }

}
