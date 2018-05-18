package com.example.xz.mytelegraphapp.movieList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.xz.mytelegraphapp.R;
import com.example.xz.mytelegraphapp.Utils.UtilsMethod;
import com.example.xz.mytelegraphapp.adapter.MovieListAdapter;
import com.example.xz.mytelegraphapp.base.BaseFragment;
import com.example.xz.mytelegraphapp.base.BaseRecyclerViewAdapter;
import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.movieDetail.MovieDetailActivity;
import com.example.xz.mytelegraphapp.movieDetail.MovieDetailFragment;
import com.example.xz.mytelegraphapp.movieList.Impl.MovieListPresenterImpl;
import com.example.xz.mytelegraphapp.movieList.contract.MovieListContract;
import com.example.xz.mytelegraphapp.moviePoster.PosterActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xz on 16/05/2018.
 * show the list and show the one/two pane layout
 */
public class MovieListFragment extends BaseFragment<MovieListContract.MovieListView, MovieListPresenterImpl> implements MovieListContract.MovieListView, BaseRecyclerViewAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.movie_list_recycler_view)
    RecyclerView myMovieListRecyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ProgressDialog dialog;

    private Unbinder unbinder;
    private MovieListAdapter movieListAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isTwoPane = false;
    private boolean reload = false;

    private List<MovieBean.MovieItemBean> myList;

    public MovieListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieListFragment newInstance(String param1, String param2) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        myList = new ArrayList<>();
    }

    @Override
    public MovieListPresenterImpl createPresenter() {
        return new MovieListPresenterImpl();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        dialog = new ProgressDialog(view.getContext());
        dialog.setCancelable(false);
        layoutManager = new LinearLayoutManager(mActivity);
        setupRecyclerView(savedInstanceState);
    }

    private void setupRecyclerView(Bundle savedInstanceState) {
        this.myMovieListRecyclerView.setLayoutManager(layoutManager);
        if (null == savedInstanceState) {
            reload = true;
        } else {
            reload = false;
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable("LIST_STATE");
            myMovieListRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
            myList = (List<MovieBean.MovieItemBean>) savedInstanceState.getSerializable("LIST_DATA");
        }
        movieListAdapter = new MovieListAdapter(mActivity, myList, R.layout.movie_item_view);
        movieListAdapter.setOnItemClickListener(this);
        this.myMovieListRecyclerView.setAdapter(movieListAdapter);


    }

    @Override
    protected void initData() {
        mPresenter.initPresenter();
        if (reload) mPresenter.getMovies();
    }

    //     try to get details_layout, if it exist, that's mean we have the two pane model
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.details_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save list state
        outState.putParcelable("LIST_STATE", myMovieListRecyclerView.getLayoutManager().onSaveInstanceState());
        outState.putSerializable("LIST_DATA", (Serializable) myList);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void setMovies(List<MovieBean.MovieItemBean> movieItemList) {
        if (movieItemList != null) {
            myList.clear();
            myList.addAll(movieItemList);
            movieListAdapter.notifyDataSetChanged();
        }
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


    //adapter item click event
    @Override
    public void onItemClickListener(View v, int position) {
        if (v.getId() == R.id.movie_poster_iv) {
            Intent intent = new Intent(getActivity(), PosterActivity.class);
            intent.putExtra("WEB_URL", myList.get(position).getPicture_url());
            startActivity(intent);
        } else {
            if (isTwoPane) {//show the fragment on two pane model(Tablet)
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("Detail");
                if (fragment == null) {
                    fragment = MovieDetailFragment.newInstance(myList.get(position), "");
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.details_layout, fragment, "Detail")
                            .commit();
                }
            } else {//go to another activity for single pane model(mobile)
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("MyMovie", (Serializable) myList.get(position));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View view) {

    }
}
