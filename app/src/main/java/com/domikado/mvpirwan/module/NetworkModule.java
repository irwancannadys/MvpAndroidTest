package com.domikado.mvpirwan.module;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 11:34 AM.

import com.domikado.mvpirwan.BuildConfig;
import com.domikado.mvpirwan.networking.ApiService;
import com.domikado.mvpirwan.networking.Service;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {

    File cacheFile;

    public NetworkModule(File cache) {
        this.cacheFile = cache;
    }

    @Provides
    @Singleton
    Retrofit provideCall(){
        Cache cache = null;
        try{
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e){
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        Request request1 = request.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .build();

                        okhttp3.Response response = chain.proceed(request1);
                        response.cacheResponse();
                        return response;
                    }
                }) .cache(cache)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public Service provideService(ApiService apiService){
        return new Service(apiService);
    }
}
