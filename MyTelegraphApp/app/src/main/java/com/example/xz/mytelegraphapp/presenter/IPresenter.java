package com.example.xz.mytelegraphapp.presenter;

import com.example.xz.mytelegraphapp.view.IView;

import io.reactivex.disposables.Disposable;

/**
 * @Description Presenter layer in MVP
 */

public interface IPresenter<T extends IView> {

    //bundle the V layer
    void attachView(T view);

    //cancel the bundle with V layer to avoid the memory leak
    void detachView();

    //add the network reqeust to compositeDisposable
    void addDisposable(Disposable subscription);

    //delete all the request
    void unDisposable();
}
