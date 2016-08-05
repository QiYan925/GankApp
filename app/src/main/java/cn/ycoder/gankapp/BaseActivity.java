package cn.ycoder.gankapp;

import android.app.Dialog;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import cn.ycoder.gankapp.utils.DialogHelp;
import cn.ycoder.gankapp.utils.UiUtils;

/**
 * Created by Yu on 2016/8/5.
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    private Dialog loadDialog;

    /**
     * 显示加载dialog
     */
    public void showLoadingDialog(boolean cancelable) {
        this.loadDialog = DialogHelp.createLoadingDialog(this);
        this.loadDialog.setCancelable(cancelable);
        this.loadDialog.show();
    }

    /**
     * 显示加载dialog
     */
    public void showLoadingDialog() {
        showLoadingDialog(false);
    }

    /**
     * 隐藏加载dialog（所有集成BaseSubscriber的方法，在onError和onCompleted里都会自动处理下）
     */
    public void hideLoadingDialog() {
        if (loadDialog != null) {
            loadDialog.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoadingDialog();
        UiUtils.hideSoftKeyboard(getCurrentFocus());
    }
}
