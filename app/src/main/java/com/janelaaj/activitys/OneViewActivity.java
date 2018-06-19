package com.janelaaj.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.janelaaj.R;
import com.janelaaj.adapter.DayFeedsAdapter;
import com.janelaaj.adapter.OneViewAdapter;
import com.janelaaj.model.FeedsItem;
import com.janelaaj.model.OneViewItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created On 18-05-2018
 *
 * @author NarayanSemwal.
 */
public class OneViewActivity extends AppCompatActivity implements View.OnClickListener {


    private List<OneViewItems> feedsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private OneViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneview_screen);
        recyclerView = findViewById(R.id.onerecycler_view);

        mAdapter = new OneViewAdapter(feedsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        OneViewItems feedsItem = new OneViewItems("Clinic 1 Address");
        feedsList.add(feedsItem);

        OneViewItems feedsItem1 = new OneViewItems("Clinic 2 Address");
                feedsList.add(feedsItem1);

        OneViewItems feedsItem2 = new OneViewItems("Clinic 3 Address");
        feedsList.add(feedsItem2);

        OneViewItems feedsItem3 = new OneViewItems("Clinic 4 Address");
        feedsList.add(feedsItem3);

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

    }


}
