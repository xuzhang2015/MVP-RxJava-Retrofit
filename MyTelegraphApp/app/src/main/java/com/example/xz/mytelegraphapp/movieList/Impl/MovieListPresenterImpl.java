package com.example.xz.mytelegraphapp.movieList.Impl;

import com.example.xz.mytelegraphapp.base.BasePresenter;
import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.model.Api;
import com.example.xz.mytelegraphapp.movieList.contract.MovieListContract;
import com.example.xz.mytelegraphapp.retrofit.ExceptionHelper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xz on 16/05/2018.
 * presenter to get movies
 */
public class MovieListPresenterImpl extends BasePresenter<MovieListContract.MovieListView> implements MovieListContract.MovieListPresenter {

    private MovieListContract.MovieListView mView;

    //for unit test only
    public MovieListPresenterImpl(MovieListContract.MovieListView mView) {
        this.mView = mView;
    }

    public MovieListPresenterImpl() {
    }


    @Override
    public void initPresenter() {
        mView = getView();
    }

    @Override
    public void getMovies() {
        Api.getInstance().test()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        mView.showLoading();
                    }
                })
                .map(new Function<MovieBean, List<MovieBean.MovieItemBean>>() {
                    @Override
                    public List<MovieBean.MovieItemBean> apply(@NonNull MovieBean movieBean) throws Exception {
                        return movieBean.getCollection();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieBean.MovieItemBean>>() {
                    @Override
                    public void accept(@NonNull List<MovieBean.MovieItemBean> movieItemBeans) throws Exception {
                        mView.dismissLoading();
                        mView.setMovies(movieItemBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.dismissLoading();
                        ExceptionHelper.handleException(throwable);
                    }
                });
    }

}
