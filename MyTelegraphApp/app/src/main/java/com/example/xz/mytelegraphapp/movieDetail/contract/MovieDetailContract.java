package com.example.xz.mytelegraphapp.movieDetail.contract;

import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.view.IView;

/**
 * Created by xz on 16/05/2018.
 */
public interface MovieDetailContract {
    interface MovieDetailPresenter {
        void initPresenter();

        void getWebPageUrl(MovieBean.MovieItemBean movieItemBean);
    }

    interface MovieDetailView extends IView {
        void showWebpage(String url);

        void showLoadFailMsg(String msg);
    }
}
