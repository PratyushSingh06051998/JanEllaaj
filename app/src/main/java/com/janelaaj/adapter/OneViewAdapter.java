package com.janelaaj.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.janelaaj.R;
import com.janelaaj.model.FeedsItem;
import com.janelaaj.model.OneViewItems;

import java.util.List;

/**
 * Created On 25-05-2018
 *
 * @author Altametrics Inc.
 */
public class OneViewAdapter extends RecyclerView.Adapter<OneViewAdapter.MyViewHolder> {

    private List<OneViewItems> daysFeedsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView eventTextView;
        Button eventButton;
        LinearLayout feedsLayout;

        public MyViewHolder(View view) {
            super(view);
            eventTextView = view.findViewById(R.id.eventTextView);
        }
    }


    public OneViewAdapter(List<OneViewItems> daysFeedsList) {
        this.daysFeedsList = daysFeedsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_view_rowscreen, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OneViewItems feedsItem = daysFeedsList.get(position);
        holder.eventTextView.setText(feedsItem.getAddress());
    }

    @Override
    public int getItemCount() {
        return daysFeedsList.size();
    }
}