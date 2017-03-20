package com.domikado.mvpirwan.home;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 12:03 PM.

import com.domikado.mvpirwan.model.CityListResponse;

public interface MainInterface {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getcityListSuccess(CityListResponse cityListResponse);
}
