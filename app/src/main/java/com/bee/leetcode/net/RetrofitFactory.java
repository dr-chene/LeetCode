package com.bee.leetcode.net;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by dr_chene on 2021/2/4
 * desc 以工厂模式创建不同的Retrofit对象 但我们的项目中暂时只用到了一种Retrofit
 */
public class RetrofitFactory {
    //防止创建类对象
    private RetrofitFactory() {
    }

    public static final String baseUrl = "";

    //享元模式
    public static final Map<Integer, Retrofit> retrofitCacheMap = new HashMap<>();
    public static final int TYPE_NORMAL = 0;

    /**
     * @param type 要创建的retrofit对象种类
     * @return retrofit对象
     * @throws IllegalArgumentException 传入的type错误时抛出
     */
    public static Retrofit create(int type) throws IllegalArgumentException {
        switch (type) {
            case TYPE_NORMAL:
                return createNormal();
            default:
                throw new IllegalArgumentException("Unknown type!Check it carefully.");
        }
    }

    private static Retrofit createNormal() {
        Retrofit normal = retrofitCacheMap.get(TYPE_NORMAL);
        if (normal == null) {
            normal = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())//gson适配器
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//rxjava3适配器
                    .build();
            retrofitCacheMap.put(TYPE_NORMAL, normal);
        }
        return normal;
    }
}
