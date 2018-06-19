package com.janelaaj.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.janelaaj.R;
import com.janelaaj.activitys.LoginActivity;
import com.janelaaj.activitys.ManageDiscountEditActivity;
import com.janelaaj.activitys.ManageLocationActivity;
import com.janelaaj.activitys.SelectOptionScreen;
import com.janelaaj.activitys.ServicesRatesEditActivity;
import com.janelaaj.activitys.TimmingEditActivity;
import com.janelaaj.framework.IAsyncWorkCompletedCallback;
import com.janelaaj.framework.ServiceCaller;
import com.janelaaj.model.ManageLocation;
import com.janelaaj.model.managelocationcheckpoint;
import com.janelaaj.utilities.Contants;
import com.janelaaj.utilities.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    public String dlmid;
    boolean flag=false;
    public Integer gp;
    public  ArrayList<ManageLocation.LOC> locationinfo;

    private HashMap<String, List<String>> _listDataChild;
    private LinearLayout timming, services, managediscount, editlocation;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData,ArrayList<ManageLocation.LOC> LocationInfo)
     {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.locationinfo = LocationInfo;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition,int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }


        Log.i("dlmid", locationinfo.get(groupPosition).getDlmid());
        gp = groupPosition;

//        Log.i("child","child position" + String.valueOf(childPosition));
//
//        Log.i("child","parent position" + String.valueOf(groupPosition));
        Log.i("jaiajai", String.valueOf(groupPosition) + "in 1 pui");
        Log.i("jaiajai", String.valueOf(locationinfo.get(groupPosition).getDlmid()) + "in 1 pui");



        this.timming = convertView.findViewById(R.id.timming);
        this.services = convertView.findViewById(R.id.services);
        this.managediscount = convertView.findViewById(R.id.managediscount);
        this.editlocation = convertView.findViewById(R.id.editlocation);


        if (Utility.isOnline(_context)) {
            JSONObject object = new JSONObject();
            try {
                object.put("dlmid", locationinfo.get(groupPosition).getDlmid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final ProgressDialog progressbar = ProgressDialog.show(_context, "", "Please wait..", true);
            progressbar.show();
            ServiceCaller serviceCaller = new ServiceCaller(_context);
            serviceCaller.manageloctionchild(object, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        managelocationcheckpoint mainContent = new Gson().fromJson(result, managelocationcheckpoint.class);
                        if (mainContent != null) {
                            if (mainContent.getStatus().equals("SUCCESS")) {

                                Log.i("jaiajai",String.valueOf(mainContent.getProgress())+" yeah baby it is the progersss ");

                                if(mainContent.getProgress()==0){
                                    services.setEnabled(false);
                                    managediscount.setEnabled(false);
                                    timming.setEnabled(true);
                                    timming.setAlpha(1.0f);
                                    services.setAlpha(0.6f);
                                    managediscount.setAlpha(0.6f);
                                }else if(mainContent.getProgress() == 1){
                                    services.setEnabled(true);
                                    managediscount.setEnabled(false);
                                    timming.setEnabled(false);
                                    timming.setAlpha(0.6f);
                                    services.setAlpha(1.0f);
                                    managediscount.setAlpha(0.6f);
                                }else{
                                    services.setEnabled(false);
                                    managediscount.setEnabled(true);
                                    timming.setEnabled(false);
                                    timming.setAlpha(0.0f);
                                    services.setAlpha(0.6f);
                                    managediscount.setAlpha(1.0f);
                                }

                            } else {
                                Toast.makeText(_context, mainContent.getStatus().toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(_context, "User Already Registered!", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        Utility.alertForErrorMessage("User Already Registered", _context);
                    }
                    if (progressbar.isShowing()) {
                        progressbar.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, _context);//off line msg....
        }



        timming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimmingEditActivity(_context);
            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openServicesRatesEditActivity(_context);
            }
        });
        managediscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openManageDiscountEditActivity(_context);
            }
        });



        //  txtListChild.setText(childText);
        return convertView;
    }




    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        if (isExpanded) {
            Log.i("mknjbl","asd "+String.valueOf(groupPosition));
            convertView.findViewById(R.id.expand).setVisibility(View.GONE);
            convertView.findViewById(R.id.collapse).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.listGroupLayout).setBackgroundResource(R.drawable.login_border);
            lblListHeader.setTextColor(Color.parseColor("#ffffff"));
            convertView.findViewById(R.id.viewSpace).setVisibility(View.GONE);
        } else {
            Log.i("mknjbl","czx"+String.valueOf(groupPosition));
            convertView.findViewById(R.id.expand).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.collapse).setVisibility(View.GONE);
            convertView.findViewById(R.id.listGroupLayout).setBackgroundColor(Color.parseColor("#BFBFBF"));
            lblListHeader.setTextColor(Color.parseColor("#757575"));
            convertView.findViewById(R.id.viewSpace).setVisibility(View.INVISIBLE);
        }


//        if(pos != groupPosition){
//            convertView.findViewById(R.id.expand).setVisibility(View.GONE);
//            convertView.findViewById(R.id.collapse).setVisibility(View.VISIBLE);
//            convertView.findViewById(R.id.listGroupLayout).setBackgroundResource(R.drawable.login_border);
//            lblListHeader.setTextColor(Color.parseColor("#ffffff"));
//            convertView.findViewById(R.id.viewSpace).setVisibility(View.GONE);
//        }

//        for(int i=0;i<_listDataHeader.size();i++)
//        {
//            if()
//        }
//
//        Log.i("a","jai ho 1");
//        Log.i("a","grp pos"+groupPosition+" is expanded "+isExpanded);




//        if(xxx!=null) {
//            Log.i("asdzvcxv","jai ho");
//            TextView ff = xxx.findViewById(R.id.lblListHeader);
//            xxx.findViewById(R.id.expand).setVisibility(View.GONE);
//            xxx.findViewById(R.id.collapse).setVisibility(View.VISIBLE);
//            xxx.findViewById(R.id.listGroupLayout).setBackgroundResource(R.drawable.login_border);
//            ff.setTextColor(Color.parseColor("#ffffff"));
//            xxx.findViewById(R.id.viewSpace).setVisibility(View.GONE);
//        }
//
//
//        xxx=convertView;





        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        Log.i("zxccvxc",String.valueOf(childPosition));
//        Log.i("zxccvxc",String.valueOf());

        return true;
    }


    public void openTimmingEditActivity(Context context) {
        Intent myactivity = new Intent(context.getApplicationContext(), TimmingEditActivity.class);
        String dlmid = locationinfo.get(gp).getDlmid();
        myactivity.putExtra("dlmid",dlmid);
        myactivity.putExtra("id",locationinfo.get(gp).getLid());
        myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(myactivity);

    }
    public void openServicesRatesEditActivity(Context context) {
        Intent myactivity = new Intent(context.getApplicationContext(), ServicesRatesEditActivity.class);
        String dlmid = locationinfo.get(gp).getDlmid();
        myactivity.putExtra("dlmid",dlmid);
        myactivity.putExtra("id",locationinfo.get(gp).getLid());
        myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(myactivity);

    }

    public void openManageDiscountEditActivity(Context context) {
        Intent myactivity = new Intent(context.getApplicationContext(), ManageDiscountEditActivity.class);
        myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(myactivity);

    }



    }
