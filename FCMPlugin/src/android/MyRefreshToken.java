package com-android-fcm-plugins;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyRefreshToken extends FirebaseInstanceIdService
{

@Override
public void onTokenRefresh() {
    // Get updated InstanceID token.
    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
     FCMPlugin.captureToken(refreshedToken);
}

}
