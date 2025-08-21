package com.example.meltingbooks;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {

    private ImageButton kakaoLoginBtn;
    private ImageButton emailLoginBtn;

    private ImageView naverLogin;
    private ImageView facebookLogin;
    private ImageView appleLogin;
    private ImageView googleLogin;

    private TextView signupText;
    private TextView findAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 상태바 색상 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        // 버튼 및 뷰 초기화
        kakaoLoginBtn = findViewById(R.id.kakaoLoginBtn);
        emailLoginBtn = findViewById(R.id.emailLoginBtn);

        naverLogin = findViewById(R.id.naver);
        facebookLogin = findViewById(R.id.facebook);
        appleLogin = findViewById(R.id.apple);
        googleLogin = findViewById(R.id.google);

        signupText = findViewById(R.id.signUp);
        findAccountText = findViewById(R.id.findAccount);

        // 카카오 로그인 버튼 클릭 이벤트
        kakaoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "카카오 로그인 클릭", Toast.LENGTH_SHORT).show();
                // TODO: 카카오 로그인 로직 구현
                Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });

        // 이메일 로그인 버튼 클릭 이벤트
        emailLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "이메일 로그인 클릭", Toast.LENGTH_SHORT).show();
                // TODO: 이메일 로그인 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });

        // 소셜 로그인 클릭 이벤트
        naverLogin.setOnClickListener(v -> Toast.makeText(this, "네이버 로그인 클릭", Toast.LENGTH_SHORT).show());
        facebookLogin.setOnClickListener(v -> Toast.makeText(this, "페이스북 로그인 클릭", Toast.LENGTH_SHORT).show());
        appleLogin.setOnClickListener(v -> Toast.makeText(this, "애플 로그인 클릭", Toast.LENGTH_SHORT).show());
        googleLogin.setOnClickListener(v -> Toast.makeText(this, "구글 로그인 클릭", Toast.LENGTH_SHORT).show());

        // 하단 링크 클릭 이벤트
        signupText.setOnClickListener(v -> {
            Toast.makeText(this, "회원가입 클릭", Toast.LENGTH_SHORT).show();
            // TODO: 회원가입 화면으로 이동
            Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
            startActivity(intent);
        });

        findAccountText.setOnClickListener(v -> {
            Toast.makeText(this, "계정 찾기 클릭", Toast.LENGTH_SHORT).show();
            // TODO: 계정 찾기 화면으로 이동
            Intent intent = new Intent(LoginActivity.this, FeedActivity.class);
            startActivity(intent);
        });
    }
}
