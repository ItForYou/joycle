package kr.co.itforone.joycle;

import android.app.Activity;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

class ClientManager extends WebViewClient {
    Activity activity;
    MainActivity mainActivity;
    ClientManager(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    ClientManager(Activity activity, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.activity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.contains("/bbs/chul_ship.php")){
            mainActivity.Norefresh();
            mainActivity.flg_refresh=0;
        }
        else{
            mainActivity.Yesrefresh();
            mainActivity.flg_refresh=1;
        }
        view.loadUrl(url);
        return true;
    }
}
