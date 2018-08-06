package com.demo.com.nyi.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by pawan on 06/8/18.
 */

public class Util {
    public static void toast(Activity a, String msg) {
        Toast.makeText(a, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Activity a, String msg) {
        Toast.makeText(a, msg, Toast.LENGTH_LONG).show();
    }
}
