package com.example.banganhbui.interview.model.net;


import com.example.banganhbui.interview.model.entity.foursquare.Example;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GetAPI {
    @GET("/v2/venues/explore")
    Call<Example> loadQuestions(@QueryMap Map<String, String> params);
}
