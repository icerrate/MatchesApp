package com.icerrate.matches.data;

import com.icerrate.matches.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author icerrate.
 */
public class RetrofitModule {

    private static RetrofitModule instance = null;

    protected RetrofitModule() {
        // Exists only to defeat instantiation.
    }

    public static RetrofitModule get() {
        if(instance == null) {
            instance = new RetrofitModule();
        }
        return instance;
    }

    public OkHttpClient providesOkHttpClient() {
        return new OkHttpClient();
    }

    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(builder.build())
                .build();
    }

    public MatchApi provideMatchApi() {
        return get().provideRetrofit(get().providesOkHttpClient()).create(MatchApi.class);
    }
}
