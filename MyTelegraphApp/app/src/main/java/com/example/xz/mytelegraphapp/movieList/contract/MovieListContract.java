package com.example.xz.mytelegraphapp.movieList.contract;

import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.view.IView;

import java.util.List;

/**
 * Created by xz on 16/05/2018.
 */

public interface MovieListContract {

    interface MovieListPresenter {
        void initPresenter();

        void getMovies();
    }

    interface MovieListView extends IView {
        void setMovies(List<MovieBean.MovieItemBean> movieItemList);

        void showLoadFailMsg(String msg);
    }
}
