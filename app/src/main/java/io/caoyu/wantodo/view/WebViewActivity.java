package io.caoyu.wantodo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.databinding.ActivityWebViewBinding;
import io.yugoal.lib_base.base.activity.BaseDataBindActivity;

public class WebViewActivity extends BaseDataBindActivity<ActivityWebViewBinding> {

    public static void show(Context context, String url,String title ) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
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

        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        if (null!=intent){
            String url = intent.getStringExtra("url");
            String title = intent.getStringExtra("title");
            if (!TextUtils.isEmpty(url)) {
                dataBind.webview.loadUrl(url);
            }
            if (!TextUtils.isEmpty(title)) {
                dataBind.toolbar.setTitle(title);
            }
        }


        dataBind.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    //dataBind.tvTitle.setText(title);
                }
            }
        });

        dataBind.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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