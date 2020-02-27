package io.koverj.agent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alpa on 2/27/20
 */
public class RetrofitClient {

    private static final String KOVERJ_URL = System.getProperty("koverj.url", "http://localhost:8086");

    public static <T> T createService(final Class<T> service) {
        return  new Retrofit.Builder()
                .baseUrl(KOVERJ_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service);
    }
}
