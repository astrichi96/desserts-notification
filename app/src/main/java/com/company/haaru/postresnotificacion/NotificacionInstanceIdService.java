package com.company.haaru.postresnotificacion;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;




/**
 * Created by Haaru on 18/11/2016.
 */

public class NotificacionInstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = "NOTICIAS";
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Token:" + token);
    }
}
