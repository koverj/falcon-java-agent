package com.kover.tests.realapp.coverage.service;

import com.kover.tests.realapp.coverage.model.LocatorResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alpa on 2/26/20
 */
public interface KoverService {

    @POST("/locators")
    Call<ResponseBody> postLocators(@Body LocatorResult locatorResult);
}
