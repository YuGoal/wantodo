package io.caoyu.wantodo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.ActivityWebViewBinding;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;
import io.yugoal.lib_common_ui.ProgressWebView;

public class WebViewActivity extends BaseDataBindActivity<ActivityWebViewBinding> {
    private static final String TAG = "WebViewActivity";
    private boolean mAnimatorOn = true;
    public static void show(Context context, String url,String title,String chapterName ) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("chapterName", chapterName);
        context.startActivity(intent);
    }

    @Override
    public void initViewModel() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBind.webview.getSettings().setJavaScriptEnabled(true);
        dataBind.webview.getSettings().setSupportZoom(true);
        dataBind.webview.getSettings().setBuiltInZoomControls(true);
        dataBind.webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        dataBind.webview.getSettings().setSupportMultipleWindows(true);
        dataBind.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //自适应屏幕
        dataBind.webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        dataBind.webview.getSettings().setLoadWithOverviewMode(true);
        dataBind.webview.setOnScrollChangedCallback(new ProgressWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                Log.d(TAG, "onScroll: "+dy);
                /*if (dy>600){
                    Log.d(TAG, "onScroll: hideBottomTv");
                    hideBottomTv();
                }else {
                    Log.d(TAG, "onScroll:showBottomTv ");
                    showBottomTv();
                }*/
            }
        });
        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        if (null!=intent){
            String url = intent.getStringExtra("url");
            String title = intent.getStringExtra("title");
            String chapterName = intent.getStringExtra("chapterName");
            if (!TextUtils.isEmpty(url)) {
                dataBind.webview.loadUrl(url);
            }
        }


        dataBind.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
            }
        });

        dataBind.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBind.webview.canGoBack()) {
                    dataBind.webview.goBack();
                } else {
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && dataBind.webview.canGoBack()) {
            dataBind.webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }
}