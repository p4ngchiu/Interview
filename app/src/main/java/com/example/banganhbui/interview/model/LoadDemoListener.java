package com.example.banganhbui.interview.model;

import com.example.banganhbui.interview.model.entity.foursquare.Example;

import java.util.List;

import retrofit2.Response;

public interface LoadDemoListener {
    void onLoadDemoSuccess( Response<Example> listDemo);
    void onLoadDemoFailure(String message);
}
