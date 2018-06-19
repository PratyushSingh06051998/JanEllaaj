package com.janelaaj.activitys;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.janelaaj.R;
import com.janelaaj.component.CircleImageView;


public class UploadDocumentActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView logoImage;
    TextView welcome_dr,proceed_further,headertitel,medical_registration,higest_qualification_degree,if_any_queries,well_be_more,thanks_regards;
    private Button edit, exit,submit_otp;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_document);
        iniView();
        setupToolbar();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_android:
                        Intent intent = new Intent(UploadDocumentActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }

                return false;

            }
        });


    }

    private void setupToolbar() {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }




    public void iniView() {
        this.exit = findViewById(R.id.exit);
        exit.setOnClickListener(this);
        this.edit = findViewById(R.id.edit);
        edit.setOnClickListener(this);


        this.welcome_dr = findViewById(R.id.welcome_dr);
        this.proceed_further = findViewById(R.id.welcome_dr);
        this.headertitel = findViewById(R.id.welcome_dr);
        this.medical_registration = findViewById(R.id.welcome_dr);
        this.higest_qualification_degree = findViewById(R.id.welcome_dr);
        this.if_any_queries = findViewById(R.id.welcome_dr);
        this.well_be_more = findViewById(R.id.welcome_dr);
        this.thanks_regards = findViewById(R.id.welcome_dr);
        this.submit_otp = findViewById(R.id.submitOTP);


        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/segoeuil.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUIBold.ttf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUI.ttf");
        Typeface tf4 = Typeface.createFromAsset(getAssets(),"fonts/seguisb.ttf");


        welcome_dr.setTypeface(tf2);
        proceed_further.setTypeface(tf1);
        headertitel.setTypeface(tf1);
        medical_registration.setTypeface(tf1);
        higest_qualification_degree.setTypeface(tf1);
        if_any_queries.setTypeface(tf1);
        well_be_more.setTypeface(tf1);
        thanks_regards.setTypeface(tf1);
        exit.setTypeface(tf1);
        edit.setTypeface(tf1);
        submit_otp.setTypeface(tf1);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.exit) {
            Intent intent = new Intent(UploadDocumentActivity.this, WelcomeRegistrationActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.edit) {
            Intent intent = new Intent(UploadDocumentActivity.this, WelcomeRegistrationActivity.class);
            startActivity(intent);
        }

    }
}
