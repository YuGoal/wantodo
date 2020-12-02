package io.caoyu.wantodo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import io.yugoal.lib_base.base.activity.MvvmActivity;
import io.yugoal.lib_base.base.viewmodel.MvvmBaseViewModel;
import io.yugoal.lib_common_ui.ProgressWebView;
import io.yugoal.lib_utils.utils.StatusBarUtils;

public class WebViewActivity extends MvvmActivity<ActivityWebViewBinding, MvvmBaseViewModel> {
    private static final String TAG = "WebViewActivity";

    public static void show(Context context, String url, String title, String chapterName) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("chapterName", chapterName);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding.webview.getSettings().setJavaScriptEnabled(true);
        viewDataBinding.webview.getSettings().setSupportZoom(true);
        viewDataBinding.webview.getSettings().setBuiltInZoomControls(true);
        viewDataBinding.webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        viewDataBinding.webview.getSettings().setSupportMultipleWindows(true);
        viewDataBinding.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //自适应屏幕
        viewDataBinding.webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        viewDataBinding.webview.getSettings().setLoadWithOverviewMode(true);
        viewDataBinding.webview.setOnScrollChangedCallback(new ProgressWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                Log.d(TAG, "onScroll: " + dy);
            }
        });
        initData();
    }

    public void initData() {
        Intent intent = getIntent();
        if (null != intent) {
            String url = intent.getStringExtra("url");
            if (!TextUtils.isEmpty(url)) {
                viewDataBinding.webview.loadUrl(url);
            }
        }


        viewDataBinding.webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
            }
        });

        viewDataBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewDataBinding.webview.canGoBack()) {
                    viewDataBinding.webview.goBack();
                } else {
                    finish();
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && viewDataBinding.webview.canGoBack()) {
            viewDataBinding.webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}
