package com.bee.leetcode.net;

import androidx.annotation.NonNull;

import com.bee.leetcode.util.SpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by dr_chene on 2021/2/4
 * desc 以工厂模式创建不同的Retrofit对象 但我们的项目中暂时只用到了一种Retrofit
 */
public class RetrofitFactory {
    private static final String TAG = "RetrofitFactory";

    //防止创建类对象
    private RetrofitFactory() {
    }

    public static final String baseUrl = "https://bmft.tech/leetcode/";
    private static OkHttpClient baseClient = null;
    private static String localToken = SpUtil.getInstance().getString("token", "");

    //存储retrofit对象
    public static final Map<Integer, Retrofit> retrofitCacheMap = new HashMap<>();
    public static final int TYPE_NORMAL = 0;

    public static <T> T createWithNormal(Class<T> service) {
        return create(TYPE_NORMAL).create(service);
    }

    /**
     * @param type 要创建的retrofit对象种类
     * @return retrofit对象
     * @throws IllegalArgumentException 传入的type错误时抛出
     */
    private static Retrofit create(int type) throws IllegalArgumentException {
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
                    .client(getBaseClientInstance())
                    .addConverterFactory(GsonConverterFactory.create())//gson适配器
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//rxjava3适配器
                    .build();
            retrofitCacheMap.put(TYPE_NORMAL, normal);
        }
        return normal;
    }

    private static OkHttpClient getBaseClientInstance() {
        if (baseClient == null) {
            synchronized (OkHttpClient.class) {
                if (baseClient == null) {
                    baseClient = new OkHttpClient.Builder()
                            .addInterceptor(new ReceivedCookiesInterceptor())
                            .addInterceptor(new AddCookiesInterceptor())
                            .build();
                }
            }
        }
        return baseClient;
    }

    private static class ReceivedCookiesInterceptor implements Interceptor {
        @NonNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());

            String token = originalResponse.header("token");
            if (token != null && !token.isEmpty() && !token.equals(localToken)) {
                synchronized (this) {
                    SpUtil.getInstance().putString("token", token);
                    localToken = token;
                }
            }

            return originalResponse;
        }
    }

    private static class AddCookiesInterceptor implements Interceptor {
        @NonNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("token", localToken);
            builder.addHeader("baseUrl", "https://bmft.tech/leetcode");
            return chain.proceed(builder.build());
        }
    }
}
