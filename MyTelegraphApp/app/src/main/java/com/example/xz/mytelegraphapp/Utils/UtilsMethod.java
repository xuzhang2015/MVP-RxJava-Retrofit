package com.example.xz.mytelegraphapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.StringRes;
import android.util.Log;
import android.widget.Toast;

import com.example.xz.mytelegraphapp.MyApplication;

import java.util.List;


/**
 * Created by xz on 16/05/2018.
 * general functions
 */

public class UtilsMethod {

    private static final String TAG = "Utils";

    public static String getStringMethod(@StringRes int stringId) {
        return MyApplication.getInstance().getApplicationContext().getResources().getString(stringId);
    }

    //simple toast method
    public static void showToast(@StringRes int stringId) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), getStringMethod(stringId), Toast.LENGTH_SHORT).show();
    }

    //simple toast method
    public static void showToast(String msg) {
        Toast.makeText(MyApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean checkInternetConnection(Context context) {
        NetworkInfo info = null;
        try {
            info = (NetworkInfo) ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage(), e);
            return false;
        }
        if (info == null) return false;
        else if (info.isConnected()) return true;
        else return false;
    }

    //
    public static String getActorString(List<String> actors) {
        String str = "Actors:\n";
        for (int i = 0; i < actors.size(); i++) {
            str += actors.get(i) + "\n";
        }
        return str;
    }

    public static boolean formatWebsite(String url) {
        if (null != url && !url.trim().equals("")) {
            if (url.trim().toLowerCase().matches("((http|https)://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?")) {
                return true;
            }
        }
        return false;
    }
}
