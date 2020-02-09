package com.student.Api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.student.ApplicationContext;
import com.student.Utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    Context context;

    public  ApiClient (Context context){
        this.context = context;
    }

    private static Retrofit retrofit;
    private static OkHttpClient client;
    public static String BASE_URL = Constants.INSTANCE.getBASE_URL();
    public static Retrofit getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder().serializeNulls().create();

        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(new AuthInterceptor())
                    .build();

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;


    }
}
