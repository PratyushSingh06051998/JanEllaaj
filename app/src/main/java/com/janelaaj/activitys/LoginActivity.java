package com.janelaaj.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.gson.Gson;
import com.janelaaj.R;
import com.janelaaj.component.CircleImageView;
import com.janelaaj.framework.IAsyncWorkCompletedCallback;
import com.janelaaj.framework.ServiceCaller;
import com.janelaaj.framework.IAsyncWorkCompletedCallback;
import com.janelaaj.framework.ServiceCaller;
import com.janelaaj.model.sign_in;

import com.janelaaj.utilities.Contants;
import com.janelaaj.utilities.Utility;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private TextView headertitel, headersubtitle, problemSiging, singnUp, or;
    private EditText emailIdView, passwordView;
    private Button loginButton;
    ImageButton facebookButton, googleButton;
    private CircleImageView logoImage;
    View loginLayout;

    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        buildGoogleApiClient();
        iniView();
        emailIdView = findViewById(R.id.emailIdView);
        passwordView = findViewById(R.id.passwordView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailIdView.getText().toString().trim();
                final String password = passwordView.getText().toString().trim();
                userValidate(email,password);
            }
        });




        Typeface tf1 = Typeface.createFromAsset(getAssets(),"fonts/segoeuil.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUIBold.ttf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),"fonts/SegoeUI.ttf");
        Typeface tf4 = Typeface.createFromAsset(getAssets(),"fonts/seguisb.ttf");

        headertitel.setTypeface(tf4);
        headersubtitle.setTypeface(tf3);
        problemSiging.setTypeface(tf4);
        singnUp.setTypeface(tf4);
        loginButton.setTypeface(tf4);
        or.setTypeface(tf3);

    }

    public void iniView() {
        Role = getIntent().getStringExtra("Role");

        this.headertitel = this.findViewById(R.id.headertitel);
        this.headersubtitle = this.findViewById(R.id.headersubtitle);
        this.problemSiging = this.findViewById(R.id.problemSiging);
        this.singnUp = this.findViewById(R.id.singnUp);
//        this.emailIdView = this.findViewById(R.id.emailIdView);
//        this.passwordView = this.findViewById(R.id.passwordView);
        this.loginButton = this.findViewById(R.id.loginButton);
        this.facebookButton = this.findViewById(R.id.facebookButton);
        this.googleButton = this.findViewById(R.id.googleButton);
        this.logoImage = this.findViewById(R.id.logoImage);
        this.loginLayout = this.findViewById(R.id.loginLayout);
        this.or = this.findViewById(R.id.or);

        this.problemSiging.setOnClickListener(this);
        this.singnUp.setOnClickListener(this);
//        this.emailIdView.setOnClickListener(this);
//        this.passwordView.setOnClickListener(this);
//        this.loginButton.setOnClickListener(this);
        this.facebookButton.setOnClickListener(this);
        this.googleButton.setOnClickListener(this);


    }

    private void userValidate(final String email,final String password) {
        if (Utility.isOnline(this)) {
            JSONObject object = new JSONObject();
            try {
                object.put("email", email);
                object.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String url = Contants.SERVICE_BASE_URL + Contants.login;
            final ProgressDialog progressbar = ProgressDialog.show(LoginActivity.this, "", "Please wait..", true);
            progressbar.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.userLogin(object, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        sign_in mainContent = new Gson().fromJson(result, sign_in.class);
                        if (mainContent != null) {
                            if (mainContent.getStatus().equals("SUCCESS")) {


                                Integer x= mainContent.getCheckpoint();
                                Log.i("checkpoint",String.valueOf(x));

                                if(mainContent.getCheckpoint()==1)
                                {
                                    startActivity(new Intent(LoginActivity.this,SelectOptionScreen.class));
                                }
                                else
                                {
                                    startActivity(new Intent(LoginActivity.this,ManageLocationActivity.class));
                                }
//                                Toast.makeText(LoginActivity.this, String.valueOf(mainContent.getCheckpoint()), Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(LoginActivity.this, mainContent.getStatus().toString(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "User Already Registered!", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        Utility.alertForErrorMessage("User Already Registered", LoginActivity.this);
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

    public void buildGoogleApiClient() {
        if (mGoogleApiClient == null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(Contants.LOG_TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(Contants.LOG_TAG, "display name: " + acct.getDisplayName());
            String personName = acct.getDisplayName();
            // String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            String mAccessToken = acct.getId();
            String providerType = "gmail";
            Intent intent = new Intent(LoginActivity.this, GoogleLoginActivity.class);
            //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Email", email);
            intent.putExtra("PersonName", personName);
            intent.putExtra("AccessToken", mAccessToken);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(Contants.LOG_TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            //showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    // hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(Contants.LOG_TAG, "onConnectionFailed:" + connectionResult);
        buildGoogleApiClient();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.facebookButton) {
            openfbLoginActivity();
        } else if (v.getId() == R.id.googleButton) {
            signIn();
        } else if (v.getId() == R.id.loginButton) {
            Intent intent = new Intent(LoginActivity.this, SelectOptionScreen.class);
            //  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (v.getId() == R.id.singnUp) {
            Intent intent = new Intent(LoginActivity.this, SelectTYpeActivity.class);
            intent.putExtra("Role", Role);
            startActivity(intent);
        }


    }

    public void openfbLoginActivity() {
        Intent intent = new Intent(LoginActivity.this, FBLoginActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




}
