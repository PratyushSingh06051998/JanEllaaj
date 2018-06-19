package com.janelaaj.activitys;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.janelaaj.R;
import com.janelaaj.adapter.RegistrationPagerAdapter;
import com.janelaaj.component.CircleImageView;
import com.janelaaj.framework.IAsyncWorkCompletedCallback;
import com.janelaaj.framework.ServiceCaller;
import com.janelaaj.model.MainContent;
import com.janelaaj.utilities.Contants;
import com.janelaaj.utilities.Utility;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileRegistrationActivity extends AppCompatActivity {

    private TextView headertitel, headersubtitle, codeTextView, doctor, profile_registration;
    private CircleImageView profileimage;
    LinearLayout pagerIndicator;
    Spinner typeSpinneer;


    private ViewPager viewPager;
    private RegistrationPagerAdapter myViewPagerAdapter;
    private ImageView[] dots;
    //   private int[] layouts;

    Spinner s1, s2, s3;
    String nameVieStr, dobViewStr, emailidViewStr, passwordViewStr;
    String gender;
    String MedicalregiNo, ExperinceView, RegistrationYear, RegistrationCouncil, Specialization;
    String phoneNo = "";
    String Role;
    String email;
    String PersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.registration_viewpager);

        phoneNo = getIntent().getStringExtra("PhoneNo");
        Role = getIntent().getStringExtra("Role");

        email = getIntent().getStringExtra("Email");
        PersonName = getIntent().getStringExtra("PersonName");

        viewPager = this.findViewById(R.id.view_pager);
        pagerIndicator = this.findViewById(R.id.viewPagerCountDots);
        profileimage = this.findViewById(R.id.profileimage);

        doctor = this.findViewById(R.id.doctor);
        profile_registration = this.findViewById(R.id.profile_registration);
        s1 = this.findViewById(R.id.specilization_nameLayout);
        s2 = this.findViewById(R.id.medicalNo_nameLayout);
        s3 = this.findViewById(R.id.regicouncle_nameLayout);


      /*  layouts = new int[]{
                R.layout.registrationfirst_page,
                R.layout.registration_second_page,
        };*/

        changeStatusBarColor();

        //myViewPagerAdapter = new MyViewPagerAdapter();
        myViewPagerAdapter = new RegistrationPagerAdapter(getSupportFragmentManager(),email,PersonName);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        setUiPageViewController();




        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/segoeuil.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUIBold.ttf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUI.ttf");
        Typeface tf4 = Typeface.createFromAsset(getAssets(),"fonts/seguisb.ttf");
        doctor.setTypeface(tf3);
        profile_registration.setTypeface(tf3);



    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            if (dotsCount <= 1 || position > dots.length) {
                return;
            }
            for (int i = 0; i < dotsCount; i++) {
                dots[i].setImageResource(R.drawable.nonselected_dot);
            }
            dots[position].setImageResource(R.drawable.selected_dot);
            if (position == myViewPagerAdapter.getCount() - 1) {
                //btnNext.setText("Register");
                //nexticon.setVisibility(View.GONE);
                profileimage.setVisibility(View.GONE);
            } else {
                //  btnNext.setText("Next");
                //  nexticon.setVisibility(View.VISIBLE);
                profileimage.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private int dotsCount;

    private void setUiPageViewController() {
        pagerIndicator.removeAllViews();
        dotsCount = myViewPagerAdapter.getCount();
        if (dotsCount <= 1) {
            pagerIndicator.setVisibility(View.INVISIBLE);
            return;
        }

        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.nonselected_dot);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(6, 0, 6, 0);
            pagerIndicator.addView(dots[i], params);
        }
        dots[0].setImageResource(R.drawable.selected_dot);
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //for geting next previous click action
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("ViewPageChange")) {
                boolean DoneFlag = intent.getBooleanExtra("DoneFlag", false);
                boolean NextPreviousFlag = intent.getBooleanExtra("NextPreviousFlag", false);

                if (NextPreviousFlag) {
                    nameVieStr = intent.getStringExtra("Name");
                    dobViewStr = intent.getStringExtra("DOB");
                    emailidViewStr = intent.getStringExtra("Email");
                    passwordViewStr = intent.getStringExtra("Password");
                    gender = intent.getStringExtra("Gender");

                    int currentPage = viewPager.getCurrentItem();
                    viewPager.setCurrentItem(currentPage + 1, true);
                } else if (DoneFlag) {
                    //second page data
                    MedicalregiNo = intent.getStringExtra("MedicalregiNo");
                    ExperinceView = intent.getStringExtra("ExperinceView");
                    RegistrationYear = intent.getStringExtra("RegistrationYear");
                    RegistrationCouncil = intent.getStringExtra("RegistrationCouncil");
                    Specialization = intent.getStringExtra("Specialization");

                    sendData();
                } else {
                    int currentPagepre = viewPager.getCurrentItem();
                    if (currentPagepre > 0) {
                        viewPager.setCurrentItem(currentPagepre - 1, true);
                    }
                }

            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("ViewPageChange"));
    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    //for hid keyboard when tab outside edittext box
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * View pager adapter
     */
   /* public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        public MyViewPagerAdapter() {
        }

        *//*  R.layout.registrationfirst_page,
          R.layout.registration_second_page,
  *//*
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          *//*  if (R.layout.registrationfirst_page == layouts[position]) {

            }else{

            }*//*
            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }*/
    private void sendData() {
        if (Utility.isOnline(this)) {
            final ProgressDialog progressbar = ProgressDialog.show(ProfileRegistrationActivity.this, "", "Please wait..", true);
            progressbar.show();
            JSONObject object = new JSONObject();
            try {
                object.put("mobile", "9383878575");
                object.put("pldrole", "DOC");
                object.put("name", nameVieStr);
                object.put("gender", gender);
                object.put("dob", dobViewStr);
                object.put("email", emailidViewStr);
                object.put("password", passwordViewStr);
                object.put("specialityid", Specialization);
                object.put("registernumber", MedicalregiNo);
                object.put("registercouncil", RegistrationCouncil);
                object.put("registeryear", RegistrationYear);
                object.put("experience", ExperinceView);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.SignUpService(object, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        MainContent mainContent = new Gson().fromJson(result, MainContent.class);
                        if (mainContent != null) {
                            if (mainContent.getStatus().equals("SUCCESS")) {
                                Toast.makeText(ProfileRegistrationActivity.this, "Registration successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ProfileRegistrationActivity.this, SelectOptionScreen.class);
//                                intent.putExtra("doctorid",mainContent.getId());

                                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ProfileRegistrationActivity.this);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("doctorid",mainContent.getId());
                                editor.commit();

                                Log.i("doctorid",mainContent.getId());
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ProfileRegistrationActivity.this, "Registration not successfully!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(ProfileRegistrationActivity.this, "Registration not successfully!", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Utility.alertForErrorMessage("Registration not successfully!", ProfileRegistrationActivity.this);
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
}
