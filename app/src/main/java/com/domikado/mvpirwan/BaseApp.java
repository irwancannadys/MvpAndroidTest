package com.domikado.mvpirwan;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 11:54 AM.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.domikado.mvpirwan.component.DaggerDeps;
import com.domikado.mvpirwan.component.Deps;
import com.domikado.mvpirwan.module.NetworkModule;

import java.io.File;

public class BaseApp extends AppCompatActivity{

    Deps deps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public Deps getDeps() {
        return deps;
    }
}
