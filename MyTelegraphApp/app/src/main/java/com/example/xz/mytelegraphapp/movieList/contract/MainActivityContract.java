package com.example.xz.mytelegraphapp.movieList.contract;

import com.example.xz.mytelegraphapp.view.IView;

/**
 * Created by xz on 16/05/2018.
 */

public interface MainActivityContract {
    interface MainActivityPresenter {
        void initPresenter();
    }

    interface MainActivityView extends IView {
    }
}
