package com.kover.tests.realapp.coverage;

import com.kover.tests.realapp.coverage.service.KoverService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alpa on 2/26/20
 */
public class KoverClient {

    private static final String KOVER_URL = System.getProperty("kover.url", "http://localhost:8086");

    public KoverService koverService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KOVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(KoverService.class);
    }

}
