package com.domikado.mvpirwan.home;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 12:07 PM.

import com.domikado.mvpirwan.model.CityListResponse;
import com.domikado.mvpirwan.networking.GetCityListCallback;
import com.domikado.mvpirwan.networking.NetworkError;
import com.domikado.mvpirwan.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainPresenter {

    private final Service service;
    private final MainInterface mainView;
    private CompositeSubscription subscriptions;

    public MainPresenter(Service service, MainInterface mainView) {
        this.service = service;
        this.mainView = mainView;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList(){
        mainView.showWait();

        Subscription subscription = service.getCityList(new GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                mainView.removeWait();
                mainView.getcityListSuccess(cityListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                mainView.removeWait();
                mainView.onFailure(networkError.getMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop(){
        subscriptions.unsubscribe();
    }
}
