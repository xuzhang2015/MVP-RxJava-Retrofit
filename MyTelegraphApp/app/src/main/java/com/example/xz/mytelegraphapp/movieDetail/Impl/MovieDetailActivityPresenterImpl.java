package com.example.xz.mytelegraphapp.movieDetail.Impl;

import com.example.xz.mytelegraphapp.base.BasePresenter;
import com.example.xz.mytelegraphapp.movieDetail.contract.MovieDetailActivityContract;

/**
 * Created by xz on 16/05/2018.
 */

public class MovieDetailActivityPresenterImpl extends BasePresenter<MovieDetailActivityContract.MovieDetailActivityView>
        implements MovieDetailActivityContract.MovieDetailActivityPresenter {

    private MovieDetailActivityContract.MovieDetailActivityView mView;

    @Override
    public void initPresenter() {
        mView = getView();
    }
}
