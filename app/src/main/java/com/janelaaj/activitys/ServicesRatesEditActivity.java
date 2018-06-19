package com.janelaaj.activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.janelaaj.R;
import com.janelaaj.adapter.ServicesRateExpandableListAdapter;
import com.janelaaj.framework.IAsyncWorkCompletedCallback;
import com.janelaaj.framework.ServiceCaller;
import com.janelaaj.model.ServiceModel;
import com.janelaaj.utilities.Contants;
import com.janelaaj.utilities.FontManager;
import com.janelaaj.utilities.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServicesRatesEditActivity extends AppCompatActivity {

    ServicesRateExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    LinearLayout listGroupLayout,addserviceLayout;
    FloatingActionButton addlocation;
    Button btn_save;
    TextView rupeeIcon,manage_location_services,title_header;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    ArrayList<ServiceModel.info> ServiceInfo;
    String DlmId;
    ArrayList<String> SelectedServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.servicesrateedit_screen);

        SelectedServices =  new ArrayList<>();
        start();
        setupToolbar();

        ServiceInfo = new ArrayList<ServiceModel.info>();

        DlmId = getIntent().getExtras().getString("dlmid","");


        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_android:
                        Intent intent = new Intent(ServicesRatesEditActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }

                return false;

            }
        });
        expListView = this.findViewById(R.id.listExpandble);
        listGroupLayout=this.findViewById(R.id.listGroupLayout);
        addserviceLayout=this.findViewById(R.id.addserviceLayout);
        listGroupLayout.setBackgroundResource(R.drawable.login_border);
        addlocation=this.findViewById(R.id.addlocation);
        btn_save=this.findViewById(R.id.btn_save);
        rupeeIcon=this.findViewById(R.id.rupeeIcon);
        manage_location_services = this.findViewById(R.id.manage_location_services);
        title_header = this.findViewById(R.id.titleHeader);



        addlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addserviceLayout.setVisibility(View.VISIBLE);
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addserviceLayout.setVisibility(View.GONE);
            }
        });

        prepareListData();
    }


    private void start()
    {
        final ProgressDialog progressbar = ProgressDialog.show(ServicesRatesEditActivity.this, "", "Please wait..", true);
        progressbar.show();
        userValidate();
        if (progressbar.isShowing()) {
            progressbar.dismiss();
        }

    }


    private void userValidate() {
        if (Utility.isOnline(this)) {
            JSONObject object = new JSONObject();
            try {
                object.put("", "");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            final ProgressDialog progressbar = ProgressDialog.show(ServicesRatesEditActivity.this, "", "Please wait..", true);
            progressbar.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.services(object, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ServiceModel mainContent = new Gson().fromJson(result, ServiceModel.class);
                        if (mainContent != null) {
                            if (mainContent.getStatus().equals("SUCCESS")) {


                                ServiceInfo=mainContent.getInfo();



                                Log.i("xxxx","oio "+String.valueOf(mainContent.getInfo().size()));
                                prepareListData();


                                Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/segoeuil.ttf");
                                Typeface tf2 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUIBold.ttf");
                                Typeface tf3 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUI.ttf");
                                Typeface tf4 = Typeface.createFromAsset(getAssets(),"fonts/seguisb.ttf");

                                manage_location_services.setTypeface(tf4);
                                title_header.setTypeface(tf4);
                                btn_save.setTypeface(tf3);



                                Typeface fontawesome_font = FontManager.getFontTypefaceMaterialDesignIcons(ServicesRatesEditActivity.this, "fonts/fontawesome-webfont.ttf");
                                rupeeIcon.setTypeface(fontawesome_font);
                                rupeeIcon.setText(Html.fromHtml("&#xf156;"));
                                listAdapter = new ServicesRateExpandableListAdapter(ServicesRatesEditActivity.this, listDataHeader, listDataChild,SelectedServices);
                                expListView.setAdapter(listAdapter);
                                expListView.setOnGroupClickListener(new OnGroupClickListener() {
                                    @Override
                                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                                        Log.i("mlpnko",String.valueOf(groupPosition));
                                        return false;
                                    }
                                });

                                expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

                                    @Override
                                    public void onGroupExpand(int groupPosition) {
                                        Log.i("mlpnko",String.valueOf(groupPosition)+"asd");
                                        Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

                                    @Override
                                    public void onGroupCollapse(int groupPosition) {
                                        Log.i("mlpnko",String.valueOf(groupPosition)+"asd1");
                                        Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                // Listview on child click listener
                                expListView.setOnChildClickListener(new OnChildClickListener() {

                                    @Override
                                    public boolean onChildClick(ExpandableListView parent, View v,
                                                                int groupPosition, int childPosition, long id) {
                                        Log.i("mlpnko",String.valueOf(groupPosition)+"asd2");
                                        // TODO Auto-generated method stub
                                        Toast.makeText(
                                                getApplicationContext(),
                                                listDataHeader.get(groupPosition)
                                                        + " : "
                                                        + listDataChild.get(
                                                        listDataHeader.get(groupPosition)).get(
                                                        childPosition), Toast.LENGTH_SHORT)
                                                .show();
                                        return false;
                                    }
                                });


                            } else {
                                Toast.makeText(ServicesRatesEditActivity.this, "error", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(ServicesRatesEditActivity.this, "User Already Registered!", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        Utility.alertForErrorMessage("User Already Registered", ServicesRatesEditActivity.this);
                    }
                    if (progressbar.isShowing()) {
                        progressbar.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, this);//off line msg....
        }
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemptio");


        for(int i=0;i<ServiceInfo.size();i++) {
            Log.i("xxxx",String.valueOf(ServiceInfo.get(i).getSname()));
            listDataHeader.add(ServiceInfo.get(i).getSname());
            listDataChild.put(listDataHeader.get(i), top250);


        }


//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");

//        List<String> df = new ArrayList<String>();
//        df.add("The sgfdgd");
//
//
//        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), nowShowing);

    }


    private void setupToolbar() {
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

//
//    private void prepareListData() {
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        // Adding child data
//        listDataHeader.add("Blood Pressure");
//        listDataHeader.add("Consultation");
//        listDataHeader.add("E.C.G");
//        listDataHeader.add("Sugar Check");
//
//        // Adding child data
//        List<String> top250 = new ArrayList<String>();
//        top250.add("The Shawshank Redemption");
//
//        List<String> nowShowing = new ArrayList<String>();
//        nowShowing.add("The Conjuring");
//
//
//        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), nowShowing);
//        listDataChild.put(listDataHeader.get(2), nowShowing);
//        listDataChild.put(listDataHeader.get(3), nowShowing);
//    }
}
