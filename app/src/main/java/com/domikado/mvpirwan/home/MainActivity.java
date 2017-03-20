package com.domikado.mvpirwan.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.domikado.mvpirwan.BaseApp;
import com.domikado.mvpirwan.R;
import com.domikado.mvpirwan.model.CityListData;
import com.domikado.mvpirwan.model.CityListResponse;
import com.domikado.mvpirwan.networking.Service;

import javax.inject.Inject;

public class MainActivity extends BaseApp implements MainInterface {

    private RecyclerView list;

    @Inject
    public Service service;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDeps().inject(this);

        list = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        init();

        MainPresenter presenter = new MainPresenter(service, this);
        presenter.getCityList();
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Toast.makeText(this, appErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getcityListSuccess(CityListResponse cityListResponse) {

        MainAdapter adapter = new MainAdapter(getApplicationContext(), cityListResponse.getData(),
                new MainAdapter.OnItemClickListener() {
            @Override
            public void onClick(CityListData item) {

                Toast.makeText(MainActivity.this, item.getName() , Toast.LENGTH_SHORT).show();

            }
        });

        list.setAdapter(adapter);

    }
}
