package com.example.xz.mytelegraphapp.retrofit;

import android.net.ParseException;
import android.util.Log;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by xz on 17/05/2018.
 * custom the exception to catch the error
 */


public class ExceptionHelper {
    public static String handleException(Throwable e) {
        e.printStackTrace();
        String error;
        if (e instanceof SocketTimeoutException) {//out of time
            Log.e("TAG", "the network connection error: " + e.getMessage());
            error = "the network connection error";
        } else if (e instanceof ConnectException) { //general error
            Log.e("TAG", "the network connection error: " + e.getMessage());

            error = "the network connection error";
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            Log.e("TAG", "json error: " + e.getMessage());
            error = "json error";
        } else if (e instanceof ApiException) {//server error
            error = e.getCause().getMessage();
        } else if (e instanceof UnknownHostException) {
            Log.e("TAG", "the network connection error: " + e.getMessage());
            error = "the network connection error";
        } else {//unknown error
            try {
                Log.e("TAG", "error: " + e.getMessage());
            } catch (Exception e1) {
                Log.e("TAG", "unknown error ");
            }
            error = "error";
        }
        return error;
    }
}
