package com.studio.suku.made;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.quinny898.library.persistentsearch.SearchBox;
import com.studio.suku.made.Adapter.ViewPagerAdapter;
import com.studio.suku.made.View.FilmFragment;
import com.studio.suku.made.View.TvFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static SearchBox searchBox;
    private static String url_destination;
    private int currentPage;
    PageListener pageListener;
    FloatingActionButton floatingActionButton;
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        actionBar = getSupportActionBar();
        actionBar.setElevation(0.5f);


        TabLayout tabLayout = findViewById(R.id.tab);
        ViewPager viewPager = findViewById(R.id.page);
        //Buat Adapeter Untuk ViewPager nya
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FilmFragment(), "Film");
        adapter.AddFragment(new TvFragment(), "Tv");
        //Kita Set ke view pager nya
        viewPager.setAdapter(adapter);
        pageListener = new PageListener();
        viewPager.setOnPageChangeListener(pageListener);
        tabLayout.setupWithViewPager(viewPager);

        Log.d("MainActicity", "Halaman : " + viewPager.getCurrentItem());


        url_destination = "https://api.themoviedb.org/3/search/movie";



    }

    @Override
    public void onClick(View v) {

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change){
            Intent pindah = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(pindah);
        }
        else if (item.getItemId() == R.id.fav_film){
            Intent pindah = new Intent(MainActivity.this, FavoriteActivity.class);
            pindah.putExtra(FavoriteActivity.Type, "Film");
            startActivity(pindah);
        }
        else if (item.getItemId() == R.id.fav_tv){
            Intent pindah = new Intent(MainActivity.this, FavoriteActivity.class);
            pindah.putExtra(FavoriteActivity.Type, "Tv");
            startActivity(pindah);
        }

        return super.onOptionsItemSelected(item);
    }

    private static class PageListener extends ViewPager.SimpleOnPageChangeListener {
        private int currentPage = 0;
        public void onPageSelected(int position) {
            Log.i("MainActivity", "page selected " + position);
            if (position == 0){
                url_destination = "https://api.themoviedb.org/3/search/movie";

            }
            else {

            }

            currentPage = position;
        }
    }


}
