package com.example.xz.mytelegraphapp.movieDetail.contract;

import com.example.xz.mytelegraphapp.view.IView;

/**
 * Created by xz on 16/05/2018.
 */

public interface MovieDetailActivityContract {
    interface MovieDetailActivityPresenter {
        void initPresenter();
    }

    interface MovieDetailActivityView extends IView {
    }
}
