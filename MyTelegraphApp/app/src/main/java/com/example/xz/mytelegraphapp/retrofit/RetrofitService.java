package com.example.xz.mytelegraphapp.retrofit;

import com.example.xz.mytelegraphapp.beans.MovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by xz on 17/05/2018.
 * retrofit service
 */

public interface RetrofitService {
    String BASE_URL = "http://s.telegraph.co.uk/tmgmobilepub/";

    @GET("articles.json")
    Observable<MovieBean> test();
}
