package io.koverj.agent.selenide.testng;

import io.koverj.agent.java.commons.LocatorsLifecycle;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.util.UUID;

/**
 * Created by alpa on 3/3/20
 */
public class KoverjListener implements IInvokedMethodListener {

    private final LocatorsLifecycle locatorsLifecycle;
    private final static String BUILD_ID = System.getProperty("koverj.build.name", UUID.randomUUID().toString());

    public KoverjListener() {
        this.locatorsLifecycle = LocatorsLifecycle.getInstance();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            locatorsLifecycle.sendLocators(BUILD_ID, method.getTestMethod().getMethodName());
        }
    }
}
