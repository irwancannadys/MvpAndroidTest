package com.domikado.mvpirwan.component;
// Created by irwancannady (irwancannady@gmail.com) on 3/19/17 at 11:51 AM.

import com.domikado.mvpirwan.home.MainActivity;
import com.domikado.mvpirwan.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface Deps {

    void inject(MainActivity mainActivity);
}
