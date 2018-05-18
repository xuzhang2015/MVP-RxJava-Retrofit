package com.example.xz.mytelegraphapp.movieList.Impl;

import com.example.xz.mytelegraphapp.base.BasePresenter;
import com.example.xz.mytelegraphapp.movieList.contract.MainActivityContract;

/**
 * Created by xz on 16/05/2018.
 */

public class MainActivityPresenterImpl extends BasePresenter<MainActivityContract.MainActivityView> implements MainActivityContract.MainActivityPresenter {
    private MainActivityContract.MainActivityView mView;

    @Override
    public void initPresenter() {
        mView = getView();
    }
}
