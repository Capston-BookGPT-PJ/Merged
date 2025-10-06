package com.example.meltingbooks.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.meltingbooks.R;
import com.example.meltingbooks.feed.FeedActivity;
import com.example.meltingbooks.network.TokenAutoRefresh;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 5000; // 5초 (원하는 시간 조절 가능)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // splash 레이아웃 적용

        // 상태바 색상 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        new Handler().postDelayed(() -> {
            SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
            String token = prefs.getString("jwt", null);
            int userId = prefs.getInt("userId", -1);

            if (token != null && userId != -1) {
                Log.d("SplashActivity", "토큰 존재: " + token + ", userId: " + userId);

                TokenAutoRefresh tokenAutoRefresh = new TokenAutoRefresh(this);
                tokenAutoRefresh.start(new TokenAutoRefresh.Callback() {
                    @Override
                    public void onSuccess(String newToken) {
                        Log.d("SplashActivity", "토큰 갱신 완료: " + newToken);
                        startActivity(new Intent(SplashActivity.this, FeedActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailure() {
                        Log.e("SplashActivity", "토큰 갱신 실패 → 로그인 화면으로 이동");
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                });
            } else {
                Log.d("SplashActivity", "토큰 없음 → 로그인 화면으로 이동");
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, SPLASH_DELAY);

    }
}
