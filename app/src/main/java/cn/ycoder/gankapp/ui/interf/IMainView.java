package cn.ycoder.gankapp.ui.interf;

import java.util.List;

import cn.ycoder.gankapp.BaseView;
import cn.ycoder.gankapp.model.Content;

/**
 * Created by Yu on 2016/8/5.
 */
public interface IMainView extends BaseView {
    /**
     * 显示内容
     */
    void showContents(List<Content> contents);
}
