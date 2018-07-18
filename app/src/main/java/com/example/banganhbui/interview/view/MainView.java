package com.example.banganhbui.interview.view;

import com.example.banganhbui.interview.model.entity.foursquare.Example;

import java.util.List;

import retrofit2.Response;

public interface MainView {
    void displayMain( Response<Example> listDemo);
    void displayErr( String err);
}
