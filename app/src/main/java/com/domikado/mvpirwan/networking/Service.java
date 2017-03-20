package com.domikado.mvpirwan.networking;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 11:11 AM.

import com.domikado.mvpirwan.model.CityListResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {

    private final ApiService apiService;

    public Service(ApiService apiService) {
        this.apiService = apiService;
    }

    public Subscription getCityList(final GetCityListCallback callback){

        return apiService.getHotel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CityListResponse>>() {
                    @Override
                    public Observable<? extends CityListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CityListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(CityListResponse cityListResponse) {
                        callback.onSuccess(cityListResponse);
                    }
                });

    }


}
