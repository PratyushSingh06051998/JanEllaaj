package com.janelaaj.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.janelaaj.R;
import com.janelaaj.activitys.TimmingEditActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ServicesRateExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles

    private HashMap<String, List<String>> _listDataChild;
    ArrayList<String> SelectedServices;

    public ServicesRateExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData,ArrayList<String> SelectedServices) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.SelectedServices = SelectedServices;
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
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.servicesedit_item, null);
        }

        Log.i("mlpnko",String.valueOf(groupPosition)+"asd5");


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
        Log.i("mlpnko",String.valueOf(groupPosition)+"asd4");
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.i("mlpnko",String.valueOf(groupPosition)+"asd3");
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.servicesedit_group, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        ImageView deleteButton = convertView.findViewById(R.id.deleteButton);
        Switch sb = convertView.findViewById(R.id.switchy);
        sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //code
                    Toast.makeText(_context, "checked", Toast.LENGTH_SHORT).show();
                }else{
                    //code
                    Toast.makeText(_context, "not checked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Log.i("mlpnko",String.valueOf(sb.isChecked()));
        Log.i("mlpnko",String.valueOf(groupPosition));

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
        return true;
    }




}
