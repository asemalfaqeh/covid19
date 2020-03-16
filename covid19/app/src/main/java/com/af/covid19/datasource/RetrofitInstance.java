package com.af.covid19.datasource;

import android.content.Context;

import com.af.covid19.utils.SecurityConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitInstance {

    private static final String BASE_URL = SecurityConstants.BASE_URL;
    private static Retrofit retrofit_secure = null;
    private static final int timeout = 180;

    protected ApiEndPoints getRetrofitInstance(Context context){

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        //TODO HTTPLoggingInterceptor//
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.interceptors().add(httpLoggingInterceptor);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        httpClientBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS) // 2 minutes
                .writeTimeout(120,TimeUnit.SECONDS)   // 2 minutes
                .readTimeout(timeout, TimeUnit.SECONDS);  // 2 minutes

        httpClientBuilder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader(SecurityConstants.HOST, SecurityConstants.API_HOST)
                    .addHeader(SecurityConstants.KEY, SecurityConstants.API_KEY)
                    .build();
            return chain.proceed(request);
        });

        if (retrofit_secure == null){
            retrofit_secure = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit_secure.create(ApiEndPoints.class);

    }

}
