package io.koverj.agent.selenide.testng;

import io.koverj.agent.java.commons.KoverjClient;
import io.koverj.agent.java.commons.SimpleLocatorStorage;
import io.koverj.agent.java.commons.config.KoverjConfig;
import io.koverj.agent.java.commons.model.Locator;
import io.koverj.agent.java.commons.model.LocatorResult;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by alpa on 3/3/20
 */
public class LocatorTestListener implements IInvokedMethodListener {

    private final SimpleLocatorStorage storage;
    private final KoverjClient koverjClient;

    public LocatorTestListener() {
        this.storage = SimpleLocatorStorage.getInstance();
        this.koverjClient = new KoverjClient();
    }


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            LocatorResult locatorResult = new LocatorResult(method.getTestMethod().getMethodName(), storage.get());
            storage.processLocator(locatorResult);
            System.out.println(locatorResult);
            if (KoverjConfig.isSendToKover) {
                koverjClient.sendLocatorsResult(locatorResult);
            }
            storage.clear();
        }
    }
}
