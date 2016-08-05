package cn.ycoder.gankapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Gank网站内容
 * Created by Yu on 2016/8/5.
 */
public class Content implements Parcelable {
    public static final String TYPE_ALL = "all";
    public static final String TYPE_ANDROID = "Android";
    public static final String TYPE_IOS = "iOS";
    public static final String TYPE_VIDEO = "休息视频";
    public static final String TYPE_BOON = "福利";
    public static final String TYPE_EXPANSION = "拓展资源";
    public static final String TYPE_FRONT_END = "前端";
    public static final String TYPE_BLIND = "瞎推荐";
    public static final String TYPE_APP = "App";
    /**
     * 提交者
     */
    public String who;
    /**
     * 发布时间
     */
    public String publishedAt;
    /**
     * 描述
     */
    public String desc;
    /**
     * 干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     */
    public String type;
    /**
     * 链接
     */
    public String url;
    public boolean used;
    public String objectId;
    /**
     * 创建时间
     */
    public String createdAt;
    /**
     * 最后修改时间
     */
    public String updatedAt;

    public Content() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.who);
        dest.writeString(this.publishedAt);
        dest.writeString(this.desc);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(this.used ? (byte) 1 : (byte) 0);
        dest.writeString(this.objectId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    protected Content(Parcel in) {
        this.who = in.readString();
        this.publishedAt = in.readString();
        this.desc = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.used = in.readByte() != 0;
        this.objectId = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    @StringDef({TYPE_ALL, TYPE_ANDROID, TYPE_IOS, TYPE_VIDEO, TYPE_BOON, TYPE_EXPANSION, TYPE_FRONT_END, TYPE_BLIND, TYPE_APP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }
}
