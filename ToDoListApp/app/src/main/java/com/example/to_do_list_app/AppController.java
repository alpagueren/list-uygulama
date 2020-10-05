package com.example.to_do_list_app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequest;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static  synchronized AppController getInstance(){
        return mInstance;
    }
    public RequestQueue getRequest(){
        if (mRequest == null){
            mRequest = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequest;
    }
    public <T> void addToRequestQueue(Request<T> reg, String tag){
        reg.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequest().add(reg);
    }
    public <T> void  addToRequestQueue(Request<T> reg){
        reg.setTag(TAG);
        getRequest().add(reg);
    }
    public void cancelPendingRequests(Object tag){
        if (mRequest != null){
            mRequest.cancelAll(tag);
        }
    }
}
