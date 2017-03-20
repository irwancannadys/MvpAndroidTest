package com.domikado.mvpirwan.networking;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 11:13 AM.

import com.domikado.mvpirwan.model.CityListResponse;

public interface GetCityListCallback {

    void onSuccess(CityListResponse cityListResponse);

    void onError(NetworkError networkError);
}
