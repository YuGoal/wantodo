package io.caoyu.wantodo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import io.caoyu.wantodo.R;
import io.caoyu.wantodo.ui.home.MainActivity;
import io.yugoal.lib_base.base.preference.SPUtils;
import io.yugoal.lib_utils.utils.AndroidUtil;
import io.yugoal.lib_utils.utils.ToastUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (isFirstEnterApp()) {
            startDialog();
        } else {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    /**
     * 是否是首次进入APP
     */
    public static boolean isFirstEnterApp() {
        return (boolean) (Boolean) SPUtils.getInstance().get("isFirstEnterApp", true);
    }

    /**
     * 保存首次进入APP状态
     */
    public static void saveFirstEnterApp() {
        SPUtils.getInstance().save("isFirstEnterApp", false);
    }

    private void startDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        alertDialog.setCancelable(false);
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_initmate);
            window.setGravity(Gravity.CENTER);

            TextView tvContent = window.findViewById(R.id.tv_content);
            TextView tvCancel = window.findViewById(R.id.tv_cancel);
            TextView tvAgree = window.findViewById(R.id.tv_agree);
            String str = "请你务必审慎阅读，充分理解“服务协议”和“隐私\n" +
                    "政策”各条款，我们将严格遵守相关法律以保护你\n" +
                    "的个人信息。\n" +
                    "为了向你提供基本服务，我们需要手机你的设备\n" +
                    "信息、操作日志等个人信息。你可以在“设置”中\n" +
                    "查看。\n" +
                    "你可阅读《服务协议》和《隐私政策》了解详细\n" +
                    "信息。\n" +
                    "如你同意，请点击“同意并继续”。";

            SpannableStringBuilder ssb = new SpannableStringBuilder();
            ssb.append(str);
            final int start = str.indexOf("《");//第一个出现的位置
            ssb.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    WebViewActivity.show(SplashActivity.this,"file:android_asset/a.html","服务协议");
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor("#11ba67"));
                    ds.setUnderlineText(false);
                }
            }, start, start + 6, 0);

            int end = str.lastIndexOf("《");
            ssb.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    WebViewActivity.show(SplashActivity.this,"file:android_asset/b.html","隐私政策");
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor("#11ba67"));
                    ds.setUnderlineText(false);
                }
            }, end, end + 6, 0);

            tvContent.setMovementMethod(LinkMovementMethod.getInstance());
            tvContent.setText(ssb, TextView.BufferType.SPANNABLE);


            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SPUtils.getInstance().save("isFirstEnterApp", true);
                    alertDialog.cancel();
                    finish();
                }
            });

            tvAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveFirstEnterApp();
                    alertDialog.cancel();
                    finish();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            });
        }

    }
}