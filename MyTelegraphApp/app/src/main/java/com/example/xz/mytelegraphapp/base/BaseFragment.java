package com.example.xz.mytelegraphapp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xz.mytelegraphapp.view.IView;

/**
 * Created by xz on 16/05/2018.
 * implement the Basepresenter and set the layout for custom fragment
 */
public abstract class BaseFragment<V extends IView, P extends BasePresenter<V>> extends Fragment {

    protected P mPresenter;
    //
    protected BaseActivity mActivity;
    private View contentView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (contentView == null) {
            contentView = inflater.inflate(getLayoutId(), container, false);
            initView(contentView, savedInstanceState);

        } else {
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if (parent != null) {
                parent.removeView(contentView);
            }
        }
        if (null == mPresenter) {
            mPresenter = createPresenter();
            //create week reference between presenter and view
            mPresenter.attachView((V) this);
        }
        initData();
        return contentView;
    }

    public abstract P createPresenter();

    //get layout Id
    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
        //delete the presenter
        mPresenter.detachView();
    }
}
