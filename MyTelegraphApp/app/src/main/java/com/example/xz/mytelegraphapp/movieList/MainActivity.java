package com.example.xz.mytelegraphapp.movieList;

import com.example.xz.mytelegraphapp.R;
import com.example.xz.mytelegraphapp.base.BaseActivity;
import com.example.xz.mytelegraphapp.movieList.Impl.MainActivityPresenterImpl;
import com.example.xz.mytelegraphapp.movieList.contract.MainActivityContract;

/**
 * Created by xz on 16/05/2018.
 * main activity
 */

public class MainActivity extends BaseActivity<MainActivityContract.MainActivityView, MainActivityPresenterImpl>
        implements MainActivityContract.MainActivityView {

    @Override
    public MainActivityPresenterImpl createPresenter() {
        return new MainActivityPresenterImpl();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter.initPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoading(String msg, int progress) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
