package com.example.xz.mytelegraphapp.movieDetail;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.xz.mytelegraphapp.R;
import com.example.xz.mytelegraphapp.base.BaseActivity;
import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.movieList.Impl.MainActivityPresenterImpl;
import com.example.xz.mytelegraphapp.movieList.contract.MainActivityContract;

/**
 * Created by xz on 16/05/2018.
 * to display the fragment with webview
 */
public class MovieDetailActivity extends BaseActivity<MainActivityContract.MainActivityView, MainActivityPresenterImpl>
        implements MainActivityContract.MainActivityView {


    @Override
    public MainActivityPresenterImpl createPresenter() {
        return new MainActivityPresenterImpl();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void initData() {
        mPresenter.initPresenter();
    }

    @Override
    protected void initView() {
        try {
            MovieBean.MovieItemBean item = (MovieBean.MovieItemBean) getIntent().getSerializableExtra("MyMovie");
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("Detail");
            if (fragment == null) {
                fragment = MovieDetailFragment.newInstance(item, "");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details_layout_fragment, fragment, "Detail")
                        .commit();
            }
        } catch (ClassCastException e) {
            Log.e(TAG, "Can't get fragment manager");
            finish();
        }
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
