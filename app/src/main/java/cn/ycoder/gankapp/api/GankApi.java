package cn.ycoder.gankapp.api;

import cn.ycoder.gankapp.model.Content;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Yu on 2016/8/5.
 */
public interface GankApi {
    /**
     * 每页展示的数据个数
     */
    int count = 10;

    /**
     * 获取随机内容列表
     *
     * @param type
     * @param page 页数
     * @return
     */
    @GET("api/data/{type}/" + count + "/{page}")
    Observable<Content> findList(@Path("type") Content.TYPE type, @Path("page") int page);

    /**
     * 关键字搜索
     *
     * @param key
     * @param page 页数
     * @return
     */
    @GET("api/search/query/{key}/category/Android/count/" + count + "/page/{page} ")
    Observable<Content> search(@Path("key") String key, @Path("page") int page);

}
