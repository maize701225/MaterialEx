package idv.hangermo.materialex.hotnews;

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
import idv.hangermo.materialex.lastest.Lastest;
import idv.hangermo.materialex.lazyroute.LazyRoute;

/**
 * Created by user on 2015/6/24.
 */
public class HotNews extends AppCompatActivity {
    private static final String urlString = "http://cat875030.pixnet.net/blog/hotarticledata?limit=15";
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton fab;
    private ListView listView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotnews_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDrawerLayout();
        initFAB();


        Bundle bundle=this.getIntent().getExtras();
        String value=bundle.getString("msg");
        listView2 = (ListView) findViewById(R.id.listView2);

    }
    @Override
    protected void onStart() {
        super.onStart();
        RetrieveRealTimeNewsTask realTimeNewsTask = new RetrieveRealTimeNewsTask();
        realTimeNewsTask.execute();
    }

    class RetrieveRealTimeNewsTask extends AsyncTask<String, Integer, List<HotNewsVO>> {

        @Override
        protected List<HotNewsVO> doInBackground(String... strings) {
            Document doc = null;
            List<HotNewsVO> newsCopyList = null;
            try {
                URL url = new URL(urlString);
                URLConnection URLConn = url.openConnection();

                //騙Server是IE6.0而不是Spider
                URLConn.setRequestProperty("User-agent", "IE/6.0");

                InputStream is = URLConn.getInputStream();
                // Parsing data --------------------------------------------
                doc = Jsoup.parse(is, "UTF-8", urlString);

                if (doc != null) {
                    List<HotNewsVO> hotnewsList = new ArrayList<>();
                    Elements title = doc.select("li"); //元素+ID與元素
                    for (Element e : title) {
                        HotNewsVO news = new HotNewsVO();
                        String hotnewstitle = e.text();
                        news.setTitle(hotnewstitle);
                        hotnewsList.add(news);
                    }

                    newsCopyList = new ArrayList<>();
                    Elements hotnewsUrl = doc.select("li > a"); //元素+ID與元素+屬性
                    for (int i = 0; i < hotnewsUrl.size(); i++) {
                        Element element = hotnewsUrl.get(i);
                        String recent_url = element.attr("href");
                        HotNewsVO news = hotnewsList.get(i);
                        news.setUrl(recent_url);
                        newsCopyList.add(news);
                    }

            /*        for (int i = 0; i < hotnewsList.size(); i++) {
                        String role = hotnewsList.get(i).getTitle();
                        Pattern pattern = Pattern.compile("\\(.*?\\)");// 查找规则公式中大括号以内的字符
                        Matcher time = pattern.matcher(role);
                        while (time.find()) {// 遍历找到的所有大括号
                            String param = time.group().replaceAll("\\(\\)", "");// 去掉括号
                            hotnewsList.get(i).setTime(param);
                        }
                        System.out.println(hotnewsList.get(i).getTime());
                    }
              */
                    //Toast.makeText(Lastest.this, newsList.get(1).getTitle(), Toast.LENGTH_SHORT).show();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsCopyList;
        }

        @Override
        protected void onPostExecute(List<HotNewsVO> result) {
            showResult(result);
        }
    }

    private void showResult(final List<HotNewsVO> result) {
        listView2.setAdapter(new NewsAdapter(this, result));
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HotNewsVO news = result.get(position);
                Uri uri = Uri.parse(news.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    class NewsAdapter extends BaseAdapter {
        private List<HotNewsVO> newsList;
        private LayoutInflater inflater;

        public NewsAdapter(Context context, List<HotNewsVO> newsList) {
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

            HotNewsVO news = newsList.get(position);
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
                switch (menuItem.getItemId()) {
                    case R.id.drawer_lastest:
                        String valueLastest = "Lastest test OK";
                        Intent intentLastest = new Intent();
                        intentLastest.setClass(HotNews.this, Lastest.class);
                        Bundle bundleLastest = new Bundle();
                        bundleLastest.putString("msg", valueLastest);
                        intentLastest.putExtras(bundleLastest);
                        startActivity(intentLastest);
                        finish();
                        break;
                    case R.id.drawer_lazyroute:
                        String valueLazyRoute = "LazyRoute test OK";
                        Intent intentLayRoute = new Intent();
                        intentLayRoute.setClass(HotNews.this, LazyRoute.class);
                        Bundle bundleLazyRoute = new Bundle();
                        bundleLazyRoute.putString("msg", valueLazyRoute);
                        intentLayRoute.putExtras(bundleLazyRoute);
                        startActivity(intentLayRoute);
                        finish();
                        break;

                    case R.id.drawer_dateplace:
                        String valueDateplace = "Dateplace test OK";
                        Intent intentDateplace = new Intent();
                        intentDateplace.setClass(HotNews.this, DatePlace.class);
                        Bundle bundleDateplace = new Bundle();
                        bundleDateplace.putString("msg", valueDateplace);
                        intentDateplace.putExtras(bundleDateplace);
                        startActivity(intentDateplace);
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

}
