package com.abraham.testinventapp.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import java.util.Objects;


public class InternetConnection {

    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    public static boolean checkConnection(@NonNull Context context) {
        return ((ConnectivityManager) Objects.requireNonNull(context.getSystemService
                (Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo() != null;
    }
}