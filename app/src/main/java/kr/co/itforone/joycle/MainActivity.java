package kr.co.itforone.joycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.webview)    WebView webView;
    @BindView(R.id.refreshlayout)    SwipeRefreshLayout refreshlayout;
    private long backPrssedTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent splash = new Intent(MainActivity.this,SplashActivity.class);
        startActivity(splash);


        webView.addJavascriptInterface(new WebviewJavainterface(this),"Android");
        webView.setWebViewClient(new ClientManager(this));
        webView.setWebChromeClient(new ChoromeManager(this, this));
        WebSettings settings = webView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setJavaScriptEnabled(true);

        refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.clearCache(true);
                webView.reload();
                refreshlayout.setRefreshing(false);
            }
        });

        refreshlayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if(webView.getScrollY() == 0){
                    refreshlayout.setEnabled(true);
                }
                else{
                    refreshlayout.setEnabled(false);
                }
            }
        });

        webView.loadUrl(getString(R.string.index));
        webView.clearCache(true);

    }

    //뒤로가기이벤트
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            long tempTime = System.currentTimeMillis();
            long intervalTime = tempTime - backPrssedTime;
            if (0 <= intervalTime && 2000 >= intervalTime){
                finish();
            }
            else
            {
                backPrssedTime = tempTime;
                Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누를시 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
