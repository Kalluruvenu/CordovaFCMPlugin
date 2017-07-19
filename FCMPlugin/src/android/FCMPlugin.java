        package com-android-fcm-plugins;

        import org.apache.cordova.CordovaPlugin;
        import org.apache.cordova.CallbackContext;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        /**
         * This class echoes a string called from JavaScript.
         */
        public class FCMPlugin extends CordovaPlugin {

            private static final String TAG = "CordovaPlugin";
            public static CordovaWebView gWebView;
            public static String notificationCallBack = "FCMPlugin.onNotificationReceived";
            public static String tokenRefreshCallBack = "FCMPlugin.onTokenRefreshReceived";
            public static Boolean notificationCallBackReady = false;
            public static Map<String, Object> lastPush = null;


            FCMPlugin(CordovaWebView gWebView)
            {
                this.gWebView = gWebView;
            }

            @Override
            public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
                if (action.equals("ready")) {

                    CallbackContext.success();
                }
                else if  (action.equals("getToken")   {

                    cordova.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            try{
                                String token = FirebaseInstanceId.getInstance().getToken();
                                callbackContext.success(token);
                            }catch(Exception e){
                                Log.d(TAG,"\tError retrieving token");
                            }
                        }
                    });
                }
                else if  (action.equals("registerNotification")   {
                 notificationCallBackReady = true;
                 cordova.getActivity().runOnUiThread(new Runnable() {
                     public void run() {
                      if(lastPush != null) FCMPlugin.sendPushPayload( lastPush );
                      lastPush = null;
                      callbackContext.success();
                  }
              });
                 return true;
             }   
             else{
                callbackContext.error("Method not found");
                return false;
            }

        }
        return false;
    }

    public static void captureToken(String token)
    {

      try {
        String callBack = "javascript:" + tokenRefreshCallBack + "('" + token + "')";
        gWebView.sendJavascript(callBack);
    } catch (Exception e) {
        Log.d(TAG,"ERROR sendRefreshToken:" , e.getMessage());
    }
}

public static void setData(Map<String, Object> payload)
{
    try {
        JSONObject jo = new JSONObject();
        for (String key : payload.keySet()) {
            jo.put(key, payload.get(key));
        }
        String callBack = "javascript:" + notificationCallBack + "(" + jo.toString() + ")";
        if(notificationCallBackReady && gWebView != null){
            gWebView.sendJavascript(callBack);
        }else {
            lastPush = payload;
        }
    } catch (Exception e) {
        Log.d(TAG, "\tERROR sendPushToView. SAVED NOTIFICATION: " + e.getMessage());
        lastPush = payload;
    }

}
}
