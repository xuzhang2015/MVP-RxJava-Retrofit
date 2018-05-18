package com.example.xz.mytelegraphapp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xz.mytelegraphapp.view.IView;

/**
 * Created by xz on 16/05/2018.
 * Implement the presenter and layout, simple the open activity and fragment method
 * Because this project does not have any function for activity, so I am not let activity to extend from the BaseActivity
 * keep the baseActivity for future use.
 */
public abstract class BaseActivity<V extends IView, P extends BasePresenter<V>> extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();
    protected P mPresenter;

    public abstract P createPresenter();

    //
    //
    protected abstract int getContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        mPresenter = createPresenter();
        //create week reference between presenter and view
        mPresenter.attachView((V) this);
        //set the content
        setContentView(getContentViewId());

        //

        initView();
        initData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //detach view and presenter connection
        mPresenter.detachView();


    }

    protected abstract void initData();

    protected abstract void initView();

    //simple the method that open new activity with bundle
    protected void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    //simple the method that open new activity without bundle
    protected void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }


}
