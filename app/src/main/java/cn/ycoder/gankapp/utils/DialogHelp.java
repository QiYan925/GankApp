package cn.ycoder.gankapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import cn.ycoder.gankapp.R;

/**
 * Created by Yu on 2016/8/5.
 */
public class DialogHelp {
    /**
     * 创建加载loading dialog
     *
     * @param activity
     * @return
     */
    public static Dialog createLoadingDialog(Activity activity) {
        Dialog dialog = new AppCompatDialog(activity);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setLayout(ScreenInfo.getPercentWidthSize(640), LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(LayoutInflater.from(activity).inflate(R.layout.dialog_loading, null));
        return dialog;
    }
}
