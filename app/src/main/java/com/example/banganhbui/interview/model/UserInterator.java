package com.example.banganhbui.interview.model;

import android.util.Log;
import android.widget.Toast;

import com.example.banganhbui.interview.model.entity.foursquare.Example;
import com.example.banganhbui.interview.model.net.GetAPI;
import com.example.banganhbui.interview.view.util.AppUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInterator {
    private String client_id = "client_id";
    private String client_secret = "client_secret";
    private String latlong = "ll";
    private String query = "query";
    private String version = "v";
    private String limit = "limit";
    private LoadDemoListener listener;

    public UserInterator(LoadDemoListener listener) {
        this.listener = listener;
    }

    public void createListData(Double lon, Double lat, String text) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppUtil.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        GetAPI getAPI = retrofit.create(GetAPI.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put(client_id, AppUtil.CLIENT_ID_KEY);
        params.put(client_secret, AppUtil.CLIENT_SECRET_KEY);
        params.put(latlong, 21.030826 + "," + 105.784484);
        params.put(query, text);
        params.put(version, AppUtil.VERSION);
        params.put(limit, AppUtil.LIMIT);
        Call<Example> call = getAPI.loadQuestions(params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                listener.onLoadDemoSuccess(response);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                listener.onLoadDemoFailure(t.getMessage());
            }
        });
    }
}
