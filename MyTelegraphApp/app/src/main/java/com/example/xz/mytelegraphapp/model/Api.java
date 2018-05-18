package com.example.xz.mytelegraphapp.model;

import com.example.xz.mytelegraphapp.retrofit.RetrofitService;

/**
 * Created by xz on 16/05/2018.
 * create the API
 */
public class Api extends BaseApiImpl {
    private static Api api = new Api(RetrofitService.BASE_URL);

    public Api(String baseUrl) {
        super(baseUrl);
    }

    public static RetrofitService getInstance() {
        return api.getRetrofit().create(RetrofitService.class);
    }
}