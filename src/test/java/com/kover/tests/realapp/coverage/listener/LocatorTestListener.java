package com.kover.tests.realapp.coverage.listener;

import com.kover.tests.realapp.coverage.KoverClient;
import com.kover.tests.realapp.coverage.model.Locator;
import com.kover.tests.realapp.coverage.model.LocatorResult;
import com.kover.tests.realapp.coverage.SimpleLocatorStorage;
import okhttp3.ResponseBody;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;


/**
 * Created by alpa on 2/25/20
 */
public class LocatorTestListener implements TestExecutionListener {

    private static final boolean isSendToKover = Boolean.parseBoolean(System.getProperty("use.kover", "true"));
    private final SimpleLocatorStorage storage;
    private final KoverClient koverClient;

    public LocatorTestListener() {
        this.storage = SimpleLocatorStorage.getInstance();
        this.koverClient = new KoverClient();
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            LocatorResult locatorResult = new LocatorResult(testIdentifier.getDisplayName(), storage.get());
            processLocator(locatorResult);
            System.out.println(locatorResult);
            if (isSendToKover) {
                sendLocatorsResult(locatorResult);
            }
            storage.clear();
        }
    }

    private void processLocator(LocatorResult locatorResult) {
        List<Locator> locators = locatorResult.getLocators();
        locators.forEach(locator -> {
            String parentUuid = locator.getParentUuid();
            if (parentUuid != null) {
//         TODO add checking for css/xpath
                locators.stream()
                        .filter(parentLocator -> parentLocator.getUuid().equalsIgnoreCase(parentUuid))
                        .findFirst()
                        .ifPresent(value -> locator.setLocator(value.getLocator() + " " + locator.getLocator()));
            }
        });
    }

    private void sendLocatorsResult(LocatorResult locatorResult) {
        Response<ResponseBody> responseBody;
        try {
            responseBody = koverClient.koverService().postLocators(locatorResult).execute();
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
