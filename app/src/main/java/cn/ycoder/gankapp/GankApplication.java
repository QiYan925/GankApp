package cn.ycoder.gankapp;

import android.app.Application;

/**
 * Created by Yu on 2016/8/5.
 */
public class GankApplication extends Application {

    private static GankApplication application;

    /**
     * 获取Application单例
     *
     * @return
     */
    public static GankApplication getInstance() {
        if (application == null) {
            new NullPointerException("MyApplication is null!");
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
