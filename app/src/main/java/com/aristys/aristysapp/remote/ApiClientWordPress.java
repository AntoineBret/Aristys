package com.aristys.aristysapp.remote;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.aristys.aristysapp.api.BaseUrl.WP_BASE_URL;


public class ApiClientWordPress {

    private static Retrofit retrofit = null;
    private Context context;

    public ApiClientWordPress(Context context) {
        setContext(context);
    }

    public ApiClientWordPress() {
    }

    public Retrofit getWPClient() {

        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = builder
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(WP_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public Context getContext() {
        return context;
    }

    public ApiClientWordPress setContext(Context context) {
        this.context = context;
        return this;
    }
}

