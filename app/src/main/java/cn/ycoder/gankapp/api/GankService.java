package cn.ycoder.gankapp.api;

import com.google.gson.GsonBuilder;

import cn.ycoder.gankapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yu on 2016/8/5.
 */
public class GankService {
    /**
     * 接口地址
     */
    private static final String API_URL = "http://gank.io/";
    private OkHttpClient client;
    private GankApi api;

    private GankService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.LOG_DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.api = retrofit.create(GankApi.class);
    }

    /**
     * 单例对象
     */
    private static GankService service;

    /**
     * 获取单例
     *
     * @return
     */
    public static GankService getInstance() {
        if (service == null) {
            synchronized (GankService.class) {
                if (service == null) {
                    service = new GankService();
                }
            }
        }
        return service;
    }

    /**
     * 获取Api调用
     *
     * @return
     */
    public GankApi get() {
        return api;
    }

}
