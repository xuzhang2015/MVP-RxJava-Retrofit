package com.example.xz.mytelegraphapp.base;

import com.example.xz.mytelegraphapp.presenter.IPresenter;
import com.example.xz.mytelegraphapp.view.IView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xz on 16/05/2018.
 * Base Presenter for system to use
 */

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {
    //View layer
    protected Reference<V> mViewRef;
    //handle the all subscription
    private CompositeDisposable mCompositeDisposable;


    //provide View and Presenter binding operations, making Presenter weak references to View to avoid memory leaks
    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }


    //cancel the bundle
    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    //return the view
    protected V getView() {
        return mViewRef.get();
    }

    //check if the activity or fragment is still binding
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    //check if the view has bundle to presenter
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    @Override
    public void addDisposable(Disposable subscription) {
        //to check if the subscribe has been bundled
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    //cancel the disposable when the UI exist to avoid the memory leak from Rx
    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }
}







