package com.example.xz.mytelegraphapp.retrofit;

/**
 * Created by xz on 17/05/2018.
 * custom the API exception to catch the error
 */

public class ApiException extends RuntimeException {

    private int code;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(String message) {
        super(new Throwable(message));

    }
}