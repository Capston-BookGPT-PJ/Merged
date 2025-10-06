package com.example.meltingbooks.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://meltingbooks.o-r.kr:8080/";
    private static Retrofit retrofit;
    private static String currentToken;

    public static Retrofit getClient(Context context, String accessToken) {
        if (retrofit == null || !accessToken.equals(currentToken)) {
            currentToken = accessToken;

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + accessToken)
                                .build();

                        Response response = chain.proceed(request);

                        if (response.code() == 401) { // Access Token 만료
                            response.close();

                            SharedPreferences prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
                            String refreshToken = prefs.getString("refreshToken", null);
                            int userId = prefs.getInt("userId", -1);

                            if (refreshToken != null && userId != -1) {
                                Log.d("ApiClient", "Access Token 만료 → refresh 시도");

                                String newAccessToken = TokenRefresher.refreshToken(context, refreshToken, userId);
                                if (newAccessToken != null) {
                                    prefs.edit().putString("jwt", newAccessToken).apply();

                                    Request newRequest = request.newBuilder()
                                            .header("Authorization", "Bearer " + newAccessToken)
                                            .build();
                                    return chain.proceed(newRequest);
                                } else {
                                    Log.e("ApiClient", "Refresh 실패 → 로그인 필요");
                                }
                            }
                        }
                        return response;
                    })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
