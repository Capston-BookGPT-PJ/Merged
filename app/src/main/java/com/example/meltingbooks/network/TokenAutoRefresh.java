package com.example.meltingbooks.network;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TokenAutoRefresh {

    private final Context context;
    private final Handler handler = new Handler();
    private final int intervalMs = 20 * 60 * 1000; // 10분
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public interface Callback {
        void onSuccess(String newToken);
        void onFailure();
    }

    public TokenAutoRefresh(Context context) {
        this.context = context;
    }

    public void start(Callback callback) {
        Log.d("TokenAutoRefresh", "자동 토큰 갱신 시작 (반복)");
        handler.post(() -> refreshTokenRepeatedly(callback));
    }

    public void stop() {
        handler.removeCallbacksAndMessages(null);
        executor.shutdownNow();
    }

    private void refreshTokenRepeatedly(Callback callback) {
        // SharedPreferences에서 refreshToken과 userId 가져오기
        var prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String refreshToken = prefs.getString("refreshToken", null);
        int userId = prefs.getInt("userId", -1);

        executor.execute(() -> {
            String newToken = TokenRefresher.refreshToken(context, refreshToken, userId);
            if (newToken != null) {
                Log.d("TokenAutoRefresh", "Access Token 갱신 완료");
                if (callback != null) callback.onSuccess(newToken);
            } else {
                Log.e("TokenAutoRefresh", "토큰 갱신 실패 → 로그인 필요");
                if (callback != null) callback.onFailure();
            }

            // 다음 갱신 예약
            handler.postDelayed(() -> refreshTokenRepeatedly(callback), intervalMs);
        });
    }
}
