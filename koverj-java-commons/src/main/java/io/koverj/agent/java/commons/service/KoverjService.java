package io.koverj.agent.java.commons.service;

import io.koverj.agent.java.commons.model.LocatorResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alpa on 2/26/20
 */
public interface KoverjService {

    @POST("/locators")
    Call<ResponseBody> postLocators(@Body LocatorResult locatorResult);
}
