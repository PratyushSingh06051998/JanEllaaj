package com.janelaaj.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.janelaaj.R;
import com.janelaaj.activitys.ProfileRegistrationActivity;
import com.janelaaj.activitys.SelectOptionScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationSecondPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationSecondPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public RegistrationSecondPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationSecondPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationSecondPageFragment newInstance(String param1, String param2) {
        RegistrationSecondPageFragment fragment = new RegistrationSecondPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private View view;
    Context context;
    String Specialization;
    String RegistrationCouncil;
    String RegistrationYear;
    Button btn_signup;
    private LinearLayout signupLayout;
    EditText medicalregiNo, experinceView;
    String medicalregiNoStr, experinceViewStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.registration_second_page, container, false);
        init();
        return view;



    }

    private void init() {
        Typeface tf1 = Typeface.createFromAsset(context.getAssets(), "fonts/segoeuil.ttf");
        medicalregiNo = (EditText) view.findViewById(R.id.medicalregiNo);
        experinceView = (EditText) view.findViewById(R.id.experinceView);
        Spinner specilization_nameLayout = (Spinner) view.findViewById(R.id.specilization_nameLayout);
        Spinner medicalNo_nameLayout = (Spinner) view.findViewById(R.id.medicalNo_nameLayout);
        Spinner regicouncle_nameLayout = (Spinner) view.findViewById(R.id.regicouncle_nameLayout);
        signupLayout = view.findViewById(R.id.signupLayout);
        btn_signup = view.findViewById(R.id.btn_signup);
        btn_signup.setTypeface(tf1);

        final String[] SpecializationArray = getResources().getStringArray(R.array.Specialization);

        final List<String> plantsList = new ArrayList<>(Arrays.asList(SpecializationArray));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getContext(),R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        specilization_nameLayout.setAdapter(spinnerArrayAdapter);


        specilization_nameLayout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp = SpecializationArray[position];
                if (sp.equals("Cardiology")) {
                    Specialization = "1";
                    //String name=nameVie.getText().toString();
                } else if (sp.equals("E.N.T")) {
                    Specialization = "2";
                    String medicalregiNostr = medicalregiNo.getText().toString();
                } else if (sp.equals("Opthalmology")) {
                    Specialization = "3";
                } else if (sp.equals("Dental")) {
                    Specialization = "4";
                } else if (sp.equals("General Physician")) {
                    Specialization = "5";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String[] Registration_CouncilArray = getResources().getStringArray(R.array.Registration_Council);
        final List<String> plantsList2 = new ArrayList<>(Arrays.asList(Registration_CouncilArray));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                getContext(),R.layout.spinner_item,plantsList2){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        medicalNo_nameLayout.setAdapter(spinnerArrayAdapter2);


        medicalNo_nameLayout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp = Registration_CouncilArray[position];
                if (sp.equals("Medical Council of India")) {
                    RegistrationCouncil = "1";
                } else if (sp.equals("Delhi Medical Council")) {
                    RegistrationCouncil = "2";
                    String medicalregiNostr = medicalregiNo.getText().toString();
                } else if (sp.equals("Punjab Medical Council")) {
                    RegistrationCouncil = "3";
                } else if (sp.equals("Karnataka Medical Council")) {
                    RegistrationCouncil = "4";
                } else if (sp.equals("UP Medical Council")) {
                    RegistrationCouncil = "5";
                } else if (sp.equals("Maharashtra Medical Council")) {
                    RegistrationCouncil = "6";
                } else if (sp.equals("Kerala Medical Council")) {
                    RegistrationCouncil = "7";
                } else if (sp.equals("Assam Medical Council")) {
                    RegistrationCouncil = "8";
                } else if (sp.equals("West Bengal Medical Council")) {
                    RegistrationCouncil = "9";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final String[] Registration_YearArray = getResources().getStringArray(R.array.Registration_Year);
        final List<String> plantsList3 = new ArrayList<>(Arrays.asList(Registration_YearArray));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter<String>(
                getContext(),R.layout.spinner_item,plantsList3){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinner_item);
        regicouncle_nameLayout.setAdapter(spinnerArrayAdapter3);


        regicouncle_nameLayout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RegistrationYear = Registration_YearArray[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()) {
                    saveScreenData(false, true);
                }
            }
        });

    }

    // ----validation -----
    private boolean isValidate() {
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        medicalregiNoStr = medicalregiNo.getText().toString();
        experinceViewStr = experinceView.getText().toString();

        if (medicalregiNoStr.length() == 0) {
            showToast("Please Enter Medical Registration Number");
            return false;
        } else if (RegistrationYear == null) {
            showToast("Select Registration Year First");
            return false;
        } else if (Specialization == null || Specialization.equals("")) {
            showToast("Select Specialization");
            return false;
        } else if (RegistrationCouncil == null || RegistrationCouncil.equals("")) {
            showToast("Select Registration Council");
            return false;
        } else if (RegistrationYear == null || RegistrationYear.equals("")) {
            showToast("Select Registration Year");
            return false;
        } else if (RegistrationYear.equals("Registration Year")) {
            showToast("Select Registration Year");
            return false;
        } else if (experinceViewStr.length() == 0) {
            showToast("Please Enter Career Experience in Years");
            return false;
        } else if (checkExpCorrectOrNot()) {
            showToast("Invalid Experience");
            return false;
        }

        return true;
    }

    private boolean checkExpCorrectOrNot() {
        Calendar now = Calendar.getInstance();   // Gets the current date and time
        int year = now.get(Calendar.YEAR);

        int totalExp = year - Integer.parseInt(RegistrationYear);
        int experince = Integer.parseInt(experinceViewStr);
        if (experince > totalExp) {
            return true;
        }
        return false;
    }

    private void saveScreenData(boolean NextPreviousFlag, boolean DoneFlag) {
        Intent intent = new Intent("ViewPageChange");
        intent.putExtra("NextPreviousFlag", NextPreviousFlag);
        intent.putExtra("DoneFlag", DoneFlag);
        intent.putExtra("MedicalregiNo", medicalregiNoStr);
        intent.putExtra("ExperinceView", experinceViewStr);
        intent.putExtra("RegistrationYear", RegistrationYear);
        intent.putExtra("RegistrationCouncil", RegistrationCouncil);
        intent.putExtra("Specialization", Specialization);


        getActivity().sendBroadcast(intent);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
