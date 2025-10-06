package com.example.meltingbooks.network.goal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GoalApi{
    @POST("/api/goals")
    Call<GoalResponse> createGoal(@Body GoalRequest request);

    @GET("/api/goals/me")
    Call<List<GoalResponse>> getGoals();

    @PUT("/api/goals/{goalId}")
    Call<GoalResponse> updateGoal(@Path("goalId") int goalId, @Body GoalRequest request);

    @DELETE("/api/goals/{goalId}")
    Call<Void> deleteGoal(@Path("goalId") int goalId);
}