package kr.co.itforone.joycle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;

class WebviewJavainterface {
    Activity activity;
    MainActivity mainActivity;


    WebviewJavainterface (MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    WebviewJavainterface(Activity activity){
        this.activity=activity;
    }

    @JavascriptInterface
    public void call_app() {
        String number ="";
        number = "tel:051-891-0088";
        mainActivity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(number)));

    }
}
