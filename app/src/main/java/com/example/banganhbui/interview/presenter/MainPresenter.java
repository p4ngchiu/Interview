package com.example.banganhbui.interview.presenter;

import com.example.banganhbui.interview.model.LoadDemoListener;
import com.example.banganhbui.interview.model.UserInterator;
import com.example.banganhbui.interview.model.entity.foursquare.Example;
import com.example.banganhbui.interview.view.MainView;

import java.util.List;

import retrofit2.Response;

public class MainPresenter implements LoadDemoListener {
    private UserInterator mainInterator;
    private MainView mainView;
    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        mainInterator = new UserInterator(this);
    }

    public void loadData(Double lon, Double lat, String text) {
        mainInterator.createListData(lon, lat, text);
    }

    @Override
    public void onLoadDemoSuccess( Response<Example> listDemo) {
        mainView.displayMain(listDemo);
    }

    @Override
    public void onLoadDemoFailure(String message) {
        mainView.displayErr(message);
    }
}
