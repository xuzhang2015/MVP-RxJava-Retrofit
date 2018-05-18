package com.example.xz.mytelegraphapp.movieDetail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.xz.mytelegraphapp.MyApplication;
import com.example.xz.mytelegraphapp.R;
import com.example.xz.mytelegraphapp.Utils.UtilsMethod;
import com.example.xz.mytelegraphapp.base.BaseFragment;
import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.movieDetail.Impl.MovieDetailPresenterImpl;
import com.example.xz.mytelegraphapp.movieDetail.contract.MovieDetailContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xz on 16/05/2018.
 * to display the webview
 */
public class MovieDetailFragment extends BaseFragment<MovieDetailContract.MovieDetailView, MovieDetailPresenterImpl> implements MovieDetailContract.MovieDetailView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.movie_detail_wv)
    WebView myWebview;
    private Bundle webViewBundle;
    private Boolean reload = false;
    private ProgressDialog dialog;
    private Unbinder unbinder;


    // TODO: Rename and change types of parameters
    private MovieBean.MovieItemBean movieItemBean;
    private String mParam2;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance(MovieBean.MovieItemBean param1, String param2) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieItemBean = (MovieBean.MovieItemBean) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public MovieDetailPresenterImpl createPresenter() {
        return new MovieDetailPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //
        unbinder = ButterKnife.bind(this, view);
        dialog = new ProgressDialog(view.getContext());
        //
        setupWebView(savedInstanceState);
    }

    private void setupWebView(Bundle savedInstanceState) {
        myWebview.getSettings().setJavaScriptEnabled(true);
        // Enable the caching for web view
        myWebview.getSettings().setAppCacheEnabled(true);
        // Specify the app cache path
        myWebview.getSettings().setAppCachePath(MyApplication.getInstance().getApplicationContext().getCacheDir().getPath());
        // Set the cache mode
        myWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissLoading();
            }
        });

        if (webViewBundle != null) {
            myWebview.restoreState(webViewBundle);
            reload = false;
        } else if (savedInstanceState != null) {
            myWebview.restoreState(savedInstanceState);
            reload = false;
        } else {
            reload = true;
        }
        webViewBundle = null;

    }

    @Override
    protected void initData() {
        mPresenter.initPresenter();
        if (reload) mPresenter.getWebPageUrl(movieItemBean);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myWebview.saveState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        myWebview.onPause();

        //sometimes the onSaveInstanceState not work properly, so I save the state here as well
        webViewBundle = new Bundle();
        myWebview.saveState(webViewBundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void showWebpage(String url) {
        myWebview.loadUrl(url);
    }

    @Override
    public void showLoadFailMsg(String msg) {
        dismissLoading();
        UtilsMethod.showToast(msg);
    }

    @Override
    public void showLoading() {
        if (dialog != null) {
            dialog.dismiss();
            dialog.show();
        }
    }

    @Override
    public void showLoading(String msg) {
        if (dialog != null) {
            dialog.dismiss();
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    @Override
    public void showLoading(String msg, int progress) {
        if (dialog != null) {
            dialog.dismiss();
            dialog.setMessage(msg);
            dialog.setProgress(progress);
            dialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (dialog != null) dialog.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        UtilsMethod.showToast(msg);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
