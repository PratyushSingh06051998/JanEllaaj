package com.janelaaj.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.janelaaj.R;

/**
 * Created On 18-05-2018
 *
 * @author NarayanSemwal.
 */
public class QuickSetsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText totime, fromtime, timelate, timelateoff;
    private TextView manageDiscountPage, timmingText;
    private ImageView discountimg, lateimg, timeoffimg;
    private Button sendNotification, sendNotificationtime;
    LinearLayout discountsubScreen, latesubScreen, timeoffsubScreen;
    CardView discountcard_view, lateCardView, timeoffCardView;
    Switch turnOffDicountSwitch, alldisountSwitch, nextLocationSwitch, totalallLocationSwitch, timeoffSwitch;
    boolean isClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quicksets_screen);
        iniView();

    }


    public void iniView() {
        this.discountcard_view = this.findViewById(R.id.discountcard_view);
        this.lateCardView = this.findViewById(R.id.lateCardView);
        this.timeoffCardView = this.findViewById(R.id.timeoffCardView);
        this.totime = this.findViewById(R.id.totime);
        this.fromtime = this.findViewById(R.id.fromtime);
        //this.timelate = this.findViewById(R.id.timelate);
        this.timelateoff = this.findViewById(R.id.timelateoff);
        this.manageDiscountPage = this.findViewById(R.id.manageDiscountPage);
        this.timmingText = this.findViewById(R.id.timmingText);

        this.sendNotification = this.findViewById(R.id.sendNotification);
        this.sendNotificationtime = this.findViewById(R.id.sendNotificationtime);

        this.discountsubScreen = this.findViewById(R.id.discountsubScreen);
        this.latesubScreen = this.findViewById(R.id.latesubScreen);
        this.timeoffsubScreen = this.findViewById(R.id.timeoffsubScreen);

        this.turnOffDicountSwitch = this.findViewById(R.id.turnOffDicountSwitch);
        this.alldisountSwitch = this.findViewById(R.id.alldisountSwitch);
        this.nextLocationSwitch = this.findViewById(R.id.nextLocationSwitch);
        this.totalallLocationSwitch = this.findViewById(R.id.totalallLocationSwitch);
        this.timeoffSwitch = this.findViewById(R.id.timeoffSwitch);

        this.discountsubScreen.setOnClickListener(this);
        this.latesubScreen.setOnClickListener(this);
        this.timeoffsubScreen.setOnClickListener(this);
        this.discountcard_view.setOnClickListener(this);
        this.lateCardView.setOnClickListener(this);
        this.timeoffCardView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.discountcard_view) {
            if (discountsubScreen.getVisibility() == View.GONE) {
                discountsubScreen.setVisibility(View.VISIBLE);
            } else {
                discountsubScreen.setVisibility(View.GONE);
            }


        } else if (v.getId() == R.id.lateCardView) {
            if (latesubScreen.getVisibility() == View.GONE) {
                latesubScreen.setVisibility(View.VISIBLE);
            } else {
                latesubScreen.setVisibility(View.GONE);
            }

        } else if (v.getId() == R.id.timeoffCardView) {
            if (timeoffsubScreen.getVisibility() == View.GONE) {
                timeoffsubScreen.setVisibility(View.VISIBLE);
            } else {
                timeoffsubScreen.setVisibility(View.GONE);
            }

        }
    }


}
