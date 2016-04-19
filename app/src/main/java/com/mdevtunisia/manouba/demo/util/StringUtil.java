package com.mdevtunisia.manouba.demo.util;

import android.text.TextUtils;

/**
 * Created by yermanov on 17/04/16.
 */
public class StringUtil {

    public static boolean isEmpty(String str) {

        if (str == null || TextUtils.isEmpty(str.trim())) {
            return true;
        } else {
            return false;
        }
    }
}
