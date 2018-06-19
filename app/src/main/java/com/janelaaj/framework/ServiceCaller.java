package com.janelaaj.framework;

import android.content.Context;
import android.util.Log;

import com.janelaaj.utilities.Contants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Neeraj on 7/25/2017.
 */
public class ServiceCaller {
    Context context;

    public ServiceCaller(Context context) {
        this.context = context;
    }

    //call login data
    public void callLoginService(String phone, String token, final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL;
        JSONObject obj = new JSONObject();
        try {
            obj.put("PhoneNumber", phone);
            obj.put("DeviceId", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(Contants.LOG_TAG, "Payload*****" + obj);
        new ServiceHelper().callService(url, obj, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    //parseAndSaveLoginData(result, workCompletedCallback);
                } else {
                    workCompletedCallback.onDone("loginService done", false);
                }
            }
        });
    }

    //parse and save login data
  /*  public void parseAndSaveLoginData(final String result, final IAsyncWorkCompletedCallback workCompletedCallback) {
        new AsyncTask<Void, Void, Boolean>() {


            @Override
            protected Boolean doInBackground(Void... voids) {
                Boolean flag = false;
                ContentData data = new Gson().fromJson(result, ContentData.class);
                Log.d(Contants.LOG_TAG, "---" + data);
                if (data != null) {
                    if (data.getData() != null) {
                        DbHelper dbhelper = new DbHelper(context);
                        dbhelper.upsertUserData(data.getData());
                        flag = true;
                    }
                }
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean flag) {
                super.onPostExecute(flag);
                if (flag) {
                    workCompletedCallback.onDone("LoginService done", true);
                } else {
                    workCompletedCallback.onDone("LoginService done", false);
                }
            }
        }.execute();
    }
*/
    //call home data
  /*  public void callHomeService(final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.HomeData;
        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("homeService done", false);
                }
            }
        });
    }
*/
    public void IOTDataService(String url, final IAsyncWorkCompletedCallback workCompletedCallback) {

        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("OtpService done", false);
                }
            }
        });
    }

    public void sendOtpService(String url, final IAsyncWorkCompletedCallback workCompletedCallback) {

        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("OtpService done", false);
                }
            }
        });
    }

    public void userVerifyservice(String url, JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {

        new ServiceHelper().callService(url, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }

    public void SignUpService(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url = Contants.SERVICE_BASE_URL + Contants.registeruser;
        new ServiceHelper().callService(url, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("SignUpService done", false);
                }
            }
        });
    }

    public void clinicaddlocation(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url2 = Contants.SERVICE_BASE_URL + Contants.clinicaddlocation;
        new ServiceHelper().callService(url2, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }

    public void Timing(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url2 = Contants.SERVICE_BASE_URL + Contants.timing;
        new ServiceHelper().callService(url2, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }

    public void ManageLocation(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url2 = Contants.SERVICE_BASE_URL + Contants.managelocation;
        new ServiceHelper().callService(url2, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }


    public void userLogin(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url2 = Contants.SERVICE_BASE_URL + Contants.login;
        new ServiceHelper().callService(url2, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }



    public void timeinformation(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url2 = Contants.SERVICE_BASE_URL + Contants.timeinformation;
        new ServiceHelper().callService(url2, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }


    public void manageloctionchild(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url2 = Contants.SERVICE_BASE_URL + Contants.checkpoint;
        new ServiceHelper().callService(url2, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }



    public void services(JSONObject object, final IAsyncWorkCompletedCallback workCompletedCallback) {
        String url2 = Contants.SERVICE_BASE_URL + Contants.services;
        new ServiceHelper().callService(url2, object, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("UserVerifyService done", false);
                }
            }
        });
    }



}
