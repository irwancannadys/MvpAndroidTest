package com.domikado.mvpirwan.networking;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 11:09 AM.

import com.domikado.mvpirwan.model.CityListResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiService {

    @GET("v1/city")
    Observable<CityListResponse> getHotel();
}
