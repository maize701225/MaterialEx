package idv.hangermo.materialex.lastest;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import idv.hangermo.materialex.R;
import idv.hangermo.materialex.dateplace.DatePlace;
import idv.hangermo.materialex.hotnews.HotNews;
import idv.hangermo.materialex.lazyroute.LazyRoute;

/**
 * Created by user on 2015/6/24.
 */
public class Lastest  extends AppCompatActivity {
    private static final String urlString = "http://cat875030.pixnet.net";
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton fab;
    private ListView listView;
    private ImageView imageView;


public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.lastest_main);
    frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    setupDrawerLayout();
    initFAB();
    listView = (ListView) findViewById(R.id.listView);
    //imageView = (ImageView) findViewById(R.id.imageView);

    Bundle bundle=getIntent().getExtras();
}
    @Override
    protected void onStart() {
         super.onStart();
         RetrieveRealTimeNewsTask realTimeNewsTask = new RetrieveRealTimeNewsTask();
         realTimeNewsTask.execute();
    }

        class RetrieveRealTimeNewsTask extends AsyncTask<String, Integer, List<LastestVO>> {

            @Override
            protected List<LastestVO> doInBackground(String... strings) {
                Document doc = null;
                List<LastestVO> newsCopyList = null;
                List<LastestVO> picnewsCopyList = null;
                try {
                    URL url = new URL(urlString);
                    URLConnection URLConn = url.openConnection();

                    //騙Server是IE6.0而不是Spider
                    URLConn.setRequestProperty("User-agent", "IE/6.0");

                    InputStream is = URLConn.getInputStream();
                    // Parsing data --------------------------------------------
                    doc = Jsoup.parse(is, "UTF-8", urlString);

                    if (doc != null) {
                        List<LastestVO> newsList = new ArrayList<>();
                        Elements title = doc.select("div#recent-article").select("li"); //元素+ID與元素
                        for (Element e : title) {
                            LastestVO news = new LastestVO();
                            String recent_title = e.text();
                            news.setTitle(recent_title);
                            newsList.add(news);
                        }

                        newsCopyList = new ArrayList<>();
                        Elements recentUrl = doc.select("div#recent-article").select("li > a"); //元素+ID與元素+屬性
                        for (int i = 0; i < recentUrl.size(); i++) {
                            Element element = recentUrl.get(i);
                            String recent_url = urlString + element.attr("href");
                            LastestVO news = newsList.get(i);
                            news.setUrl(recent_url);
                            newsCopyList.add(news);
                        }


                        picnewsCopyList = new ArrayList<>();
                        for (int i = 0; i < newsList.size(); i++) {
                            List<PicUrl> picnewsList = new ArrayList<>();
                            URL lastestUrl = new URL(newsList.get(i).getUrl());
                            URLConnection URLConnurl = lastestUrl.openConnection();

                            //騙Server是IE6.0而不是Spider
                            URLConnurl.setRequestProperty("User-agent", "IE/6.0");

                            InputStream islastest = URLConnurl.getInputStream();
                            // Parsing data --------------------------------------------
                            Document doc2 = Jsoup.parse(islastest, "UTF-8", newsList.get(i).getUrl());
                            if (doc2 != null) {
                                Elements picurl = doc2.select("[src]"); // 元素+ID與元素+屬性
                                for (Element e : picurl) {
                                    PicUrl picnews = new PicUrl();
                                    if(e.tagName().equalsIgnoreCase("img")) {
                                        //比對tag name 是否為img(忽略大小寫)
                                        String pic_url = e.attr("abs:src");
                                        picnews.setPicURL(pic_url);
                                        picnewsList.add(picnews);
                                    }
                                }

                            }
                            LastestVO news = newsList.get(i);
                            news.setPicUrl(picnewsList.get(3).getPicURL());
                            picnewsCopyList.add(news);

                            islastest.close();
                        }
                    }
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return newsCopyList;
            }

            @Override
          protected void onPostExecute(List<LastestVO> result) {
                     showResult(result);
            }
        }

        private void showResult(final List<LastestVO> result) {
            listView.setAdapter(new NewsAdapter(this, result));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LastestVO news = result.get(position);
                    Uri uri = Uri.parse(news.getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                   startActivity(intent);
                }
            });

        }

        class NewsAdapter extends BaseAdapter {
            private List<LastestVO> newsList;
            private LayoutInflater inflater;

            public NewsAdapter(Context context, List<LastestVO> newsList) {
                inflater = LayoutInflater.from(context);
                this.newsList = newsList;
            }

            @Override
            public int getCount() {
                return newsList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.listview_lastest, parent, false);

                    holder = new ViewHolder();
                    holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                    holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                LastestVO news = newsList.get(position);
                holder.tvTitle.setText(news.getTitle());
               // holder.tvTime.setText(news.getTime());
                return convertView;
            }
        }
        static class ViewHolder {
            TextView tvTitle;
            TextView tvTime;
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
                    case R.id.drawer_hotnews:
                        String valueHotnews = "Hotnews test OK";
                        Intent intentHotnews = new Intent();
                        intentHotnews.setClass(Lastest.this, HotNews.class);
                        Bundle bundleHotnews = new Bundle();
                        bundleHotnews.putString("msg", valueHotnews);
                        intentHotnews.putExtras(bundleHotnews);
                        startActivity(intentHotnews);
                        finish();
                        break;

                    case R.id.drawer_lazyroute:
                        String valueLazyRoute = "LazyRoute test OK";
                        Intent intentLayRoute = new Intent();
                        intentLayRoute.setClass(Lastest.this, LazyRoute.class);
                        Bundle bundleLazyRoute = new Bundle();
                        bundleLazyRoute.putString("msg", valueLazyRoute);
                        intentLayRoute.putExtras(bundleLazyRoute);
                        startActivity(intentLayRoute);
                        finish();
                        break;

                    case R.id.drawer_dateplace:
                        String valueDateplace = "Dateplace test OK";
                        Intent intentDateplace = new Intent();
                        intentDateplace.setClass(Lastest.this, DatePlace.class);
                        Bundle bundleDateplace = new Bundle();
                        bundleDateplace.putString("msg", valueDateplace);
                        intentDateplace.putExtras(bundleDateplace);
                        startActivity(intentDateplace);
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
