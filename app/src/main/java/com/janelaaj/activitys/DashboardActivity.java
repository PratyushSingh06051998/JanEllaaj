package com.janelaaj.activitys;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.janelaaj.R;
import com.janelaaj.adapter.NavigationMenuAdapter;
import com.janelaaj.fragment.HomeFragment;
import com.janelaaj.fragment.ProfileFragment;
import com.janelaaj.utilities.FontManager;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        TextView pIcon = (TextView) findViewById(R.id.pIcon);
        Typeface fontawesome = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/fontawesome-webfont.ttf");
        pIcon.setTypeface(fontawesome);
        pIcon.setText(Html.fromHtml("&#xf105;"));
        LinearLayout profileLayout = findViewById(R.id.profileLayout);
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = ProfileFragment.newInstance("", "");
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
                closeDrawer();
            }
        });
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUpDashboardFragment();
        setMenuLayout();
    }

    //open default fragment
    private void setUpDashboardFragment() {
        Fragment fragment = HomeFragment.newInstance("", "");
        moveFragment(fragment);
    }

    private void moveFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                //.addToBackStack(null)
                .commit();
    }

    //set slider item value
    public void setMenuLayout() {

        ListView menuList = (ListView) findViewById(R.id.lst_menu_items);
        menuList.setDivider(null);
        List<String> itemList = new ArrayList<String>();
        itemList.add("Extras");
        itemList.add("Upgrade to Premium App");
        itemList.add("24*7 Help");
        itemList.add("Second Opinion");
        itemList.add("Check for Updates");
        itemList.add("Payment Modes");
        itemList.add("Sign Out");


        NavigationMenuAdapter navigationMenuAdapter = new NavigationMenuAdapter(DashboardActivity.this, itemList);
        menuList.setAdapter(navigationMenuAdapter);
    }

    //close drawer after item select
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
        //return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId())
        {

            case R.id.nav_android:
                Toast.makeText(DashboardActivity.this, "Nav Android Selected", Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
