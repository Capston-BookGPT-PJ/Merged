package com.example.meltingbooks.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenRefresher {

    public static String refreshToken(Context context, String refreshToken, int userId) {
        if (refreshToken == null || userId == -1) {
            Log.e("TokenRefresher", "RefreshToken 또는 userId 없음");
            return null;
        }

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://meltingbooks.o-r.kr:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);

            RefreshRequest request = new RefreshRequest(userId);
            Call<RefreshResponse> call = apiService.refreshToken(request);

            Log.d("TokenRefresher", "Refresh 요청 시작 → userId: " + userId);

            Response<RefreshResponse> response = call.execute();

            if (response.isSuccessful() && response.body() != null) {
                String newAccessToken = response.body().getToken();
                String newRefreshToken = response.body().getRefreshToken();

                SharedPreferences prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
                prefs.edit()
                        .putString("jwt", newAccessToken)
                        .putString("refreshToken", newRefreshToken)
                        .apply();

                Log.d("TokenRefresher", "Access Token 갱신 완료: " + newAccessToken);
                return newAccessToken;
            } else {
                Log.e("TokenRefresher", "Refresh 실패: " + response.code() + " / " + response.message());
            }

        } catch (Exception e) {
            Log.e("TokenRefresher", "Refresh 예외 발생", e);
        }

        return null;
    }

}
