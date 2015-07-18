package idv.hangermo.materialex.lazyroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import idv.hangermo.materialex.R;
import idv.hangermo.materialex.dateplace.DatePlace;
import idv.hangermo.materialex.hotnews.HotNews;
import idv.hangermo.materialex.lastest.Lastest;

/**
 * Created by user on 2015/6/24.
 */
public class LazyRoute extends AppCompatActivity {
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton fab;

    private RecyclerView myRecyclerview;
    private RecyclerView.LayoutManager layoutManager;
    private NewsAdapter adapter;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lazyroute_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawerLayout();
        initFAB();

        textView=(TextView)findViewById(R.id.textView);

        Bundle bundle=getIntent().getExtras();
        String value=bundle.getString("msg");
        textView.setText(value);
        findViews();
    }

    private void findViews() {
        myRecyclerview = (RecyclerView)findViewById(R.id.myRecyclerview);
        //設定每個List是否為固定尺寸
        myRecyclerview.setHasFixedSize(true);
        //產生一個LinearLayoutManger
        layoutManager = new LinearLayoutManager(this);
        //設定LayoutManger
        myRecyclerview.setLayoutManager(layoutManager);

        final List<Team> teamList = new ArrayList<>();
        teamList.add(new Team(1, R.drawable.p1_1, "大村田尾花園經典1日慢遊"));
        teamList.add(new Team(2, R.drawable.p2_1, "宜蘭隨意走走1日遊"));
        teamList.add(new Team(3, R.drawable.p3_1, "超優的清境避暑之旅"));
        teamList.add(new Team(4, R.drawable.p4, "波士頓紅襪"));
        teamList.add(new Team(5, R.drawable.p5, "克里夫蘭印地安人"));

        adapter = new NewsAdapter(teamList);
        myRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                switch (teamList.get(position).getId()) {
                    case 1:
                        String valueTownship = "LazyRouteTownship test OK";
                        Intent intentTownship = new Intent();
                        intentTownship.setClass(LazyRoute.this, LazyRouteTownship.class);
                        Bundle bundleTownship = new Bundle();
                        bundleTownship.putString("msg", valueTownship);
                        intentTownship.putExtras(bundleTownship);
                        startActivity(intentTownship);
                        break;
                    case 2:
                        String valueYilan1D = "LazyRouteYilan1D test OK";
                        Intent intentYilan1D = new Intent();
                        intentYilan1D.setClass(LazyRoute.this, LazyRouteYilan1D.class);
                        Bundle bundleYilan1D = new Bundle();
                        bundleYilan1D.putString("msg", valueYilan1D);
                        intentYilan1D.putExtras(bundleYilan1D);
                        startActivity(intentYilan1D);
                        break;
                    case 3:
                        String valueCingjing = "LazyRouteCingjing test OK";
                        Intent intentCingjing = new Intent();
                        intentCingjing.setClass(LazyRoute.this, LazyRouteCingjing.class);
                        Bundle bundleCingjing = new Bundle();
                        bundleCingjing.putString("msg", valueCingjing);
                        intentCingjing.putExtras(bundleCingjing);
                        startActivity(intentCingjing);
                        break;
                    case 4:
                        Toast.makeText(LazyRoute.this, teamList.get(position).getName(), Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(LazyRoute.this, teamList.get(position).getName(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
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
                        intentLastest.setClass(LazyRoute.this, Lastest.class);
                        Bundle bundleLastest = new Bundle();
                        bundleLastest.putString("msg", valueLastest);
                        intentLastest.putExtras(bundleLastest);
                        startActivity(intentLastest);

                        finish();
                        break;
                    case R.id.drawer_hotnews:
                        String valueHotnews = "Hotnews test OK";
                        Intent intentHotnews = new Intent();
                        intentHotnews.setClass(LazyRoute.this, HotNews.class);
                        Bundle bundleHotnews = new Bundle();
                        bundleHotnews.putString("msg", valueHotnews);
                        intentHotnews.putExtras(bundleHotnews);
                        startActivity(intentHotnews);

                        finish();
                        break;

                    case R.id.drawer_dateplace:
                        String valueDateplace = "Dateplace test OK";
                        Intent intentDateplace = new Intent();
                        intentDateplace.setClass(LazyRoute.this, DatePlace.class);
                        Bundle bundleDateplace = new Bundle();
                        bundleDateplace.putString("msg", valueDateplace);
                        intentDateplace.putExtras(bundleDateplace);
                        startActivity(intentDateplace);
                        menuItem.setChecked(true);
                        finish();
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

    public void onClick(View view){
        finish();
    }
}
