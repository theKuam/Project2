package com.example.thengm.boodict;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by TheNGM on 25/10/2016.
 */


public class Controller {
    //Kiem tra ket noi
    public static boolean isNetworkConneceted(Context ct, Activity activity){
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(ct.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    //Chuan hoa xau
    public static String Normalization(String word){
        word = word.trim();
        return word.toLowerCase();
    }
    

}
