package com.example.xz.mytelegraphapp.movieList.contract;

import com.example.xz.mytelegraphapp.beans.MovieBean;
import com.example.xz.mytelegraphapp.movieList.Impl.MovieListPresenterImpl;
import com.example.xz.mytelegraphapp.retrofit.RetrofitService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xz on 17/05/2018.
 * use mockito to test the presenter
 */
public class MovieListPresenterTest {
    @Mock
    MovieListContract.MovieListView myView;
    @Mock
    MovieBean movieBean;
    @Captor
    ArgumentCaptor<ArrayList<MovieBean.MovieItemBean>> captor;
    @Mock
    RetrofitService retrofitService;

    MovieListPresenterImpl Presenter;

    @Before
    public void setUp() throws Exception {
        // init mockitoAnnotation
        MockitoAnnotations.initMocks(this);
        // check if the view is active
        when(myView.isActive()).thenReturn(true);
        //create presenter
        Presenter = new MovieListPresenterImpl(myView);
        //
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @After
    public void tearDown() throws Exception {
        myView = null;
    }

    @Test
    public void testGetMovies() throws Exception {
        //check if the return is right
        when(retrofitService.test()).thenReturn(Observable.just(movieBean));
        //check method
        Presenter.getMovies();
        verify(myView).showLoading();
        verify(myView).setMovies(captor.capture());
    }

}