package cn.ycoder.gankapp.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import cn.ycoder.gankapp.GankApplication;

/**
 * Created by Yu on 2016/4/7.
 */
public class ScreenInfo {
    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    /**
     * 屏幕的真实宽度.
     */
    public int screenWidth;

    /**
     * 屏幕的真实高度.
     */
    public int screenHeight;
    /**
     * 设计宽度
     */
    public int designWidth;
    /**
     * 设计高度
     */
    public int designHeight;
    /**
     * 状态栏高度.
     */
    public int statusBarHeight;

    /**
     * The density.
     */
    public float density;

    /**
     * The scale density.
     */
    public float scaleDensity;

    /**
     * The xdpi.
     */
    public float xdpi;

    /**
     * The ydpi.
     */
    public float ydpi;

    /**
     * The density dpi.
     */
    public int densityDpi;

    /**
     * The screen min.
     */
    public int screenMin; // 宽高中，最小的值
    /**
     * The current orientation.
     */
    private int currentOrientation;

    private static ScreenInfo screenInfo;

    public static ScreenInfo getInstance() {
        if (screenInfo == null)
            synchronized (ScreenInfo.class) {
                if (screenInfo == null) {
                    screenInfo = new ScreenInfo(GankApplication.getInstance());
                }
            }
        else if (GankApplication.getInstance() != null && screenInfo.currentOrientation != GankApplication.getInstance().getResources()
                .getConfiguration().orientation) {
            screenInfo = null;
            return getInstance();
        }
        return screenInfo;
    }

    private ScreenInfo() {

    }

    private ScreenInfo(Context context) {
        if (null == context) {
            return;
        }
        if (statusBarHeight == 0) {
            try {
                statusBarHeight = context.getResources().getDimensionPixelSize(
                        Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
            } catch (Exception e) {
                // Ignore
            }
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - statusBarHeight;
        // 每英寸多少个像素点
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        currentOrientation = context.getResources().getConfiguration().orientation;
        Log.i("ScreenInfo", "densityDpi:" + densityDpi + "," + screenWidth + "x" + screenHeight);
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                designWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                designHeight = (int) applicationInfo.metaData.get(KEY_DESIGN_HEIGHT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.", e);
        }

        Log.i("ScreenInfo", " designWidth =" + designWidth + " , designHeight = " + designHeight);
    }

    /**
     * dip转px.
     *
     * @param dipValue the dip value
     * @return the int
     */
    public static int dip2px(float dipValue) {
        final float scale = ScreenInfo.getInstance().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip.
     *
     * @param pxValue the px value
     * @return the int
     */
    public static int px2dip(float pxValue) {
        final float scale = ScreenInfo.getInstance().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取换算比例宽度
     *
     * @param val
     * @return
     */
    public static int getPercentWidthSize(int val) {
        ScreenInfo si = ScreenInfo.getInstance();
        return (int) (val * 1.0f / si.designWidth * si.screenWidth);
    }

    /**
     * 获取换算比例高度
     *
     * @param val
     * @return
     */
    public static int getPercentHeightSize(int val) {
        ScreenInfo si = ScreenInfo.getInstance();
        return (int) (val * 1.0f / si.designHeight * si.screenHeight);
    }

}
