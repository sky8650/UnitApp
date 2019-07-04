package com.xiaolei.unit.baselibrary.utils;

import android.widget.Toast;

import com.xiaolei.unit.baselibrary.base.BaseApplication;


/**
 *
 */

public class ToastUtil {

    private static Toast toast;

    public static void showToast(String errorMsg) {

        if (toast != null) {
            toast.setText(errorMsg);
        }else {
            toast = Toast.makeText(BaseApplication.getInstance(),errorMsg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
