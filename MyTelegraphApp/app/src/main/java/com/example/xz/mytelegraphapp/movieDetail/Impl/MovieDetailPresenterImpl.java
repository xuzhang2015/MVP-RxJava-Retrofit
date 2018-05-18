package com.example.xz.mytelegraphapp.movieDetail.Impl;

import com.example.xz.mytelegraphapp.Utils.UtilsMethod;
import com.example.xz.mytelegraphapp.base.BasePresenter;
import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.movieDetail.contract.MovieDetailContract;

/**
 * Created by xz on 16/05/2018.
 * to display the webview
 */
public class MovieDetailPresenterImpl extends BasePresenter<MovieDetailContract.MovieDetailView> implements MovieDetailContract.MovieDetailPresenter {

    private MovieDetailContract.MovieDetailView mView;

    @Override
    public void initPresenter() {
        mView = getView();
    }

    @Override
    public void getWebPageUrl(MovieBean.MovieItemBean movieItemBean) {
        mView.showLoading();
        if (movieItemBean != null) {
            if (UtilsMethod.formatWebsite(movieItemBean.getWebsite_url())) {
                mView.showWebpage(movieItemBean.getWebsite_url());
            } else {
                mView.showLoadFailMsg("inner error, please try again");
            }
        } else {
            mView.showLoadFailMsg("inner error, please try again");
        }

    }


}
