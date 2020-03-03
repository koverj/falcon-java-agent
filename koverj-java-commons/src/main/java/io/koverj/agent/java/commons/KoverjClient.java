package io.koverj.agent.java.commons;

import io.koverj.agent.java.commons.model.LocatorResult;
import io.koverj.agent.java.commons.service.KoverjService;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by alpa on 2/26/20
 */
public class KoverjClient {

    private KoverjService koverjService = RetrofitClient.createService(KoverjService.class);


    public void sendLocatorsResult(LocatorResult locatorResult) {
        Response<ResponseBody> responseBody;
        try {
            responseBody = koverjService.postLocators(locatorResult).execute();
            int code = responseBody.code();
            if (code != 200) {
                throw new RuntimeException("Locators not saved: " + code);
            }
            System.out.println(responseBody.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
