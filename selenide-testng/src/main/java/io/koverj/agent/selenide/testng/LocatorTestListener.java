package io.koverj.agent.selenide.testng;

import io.koverj.agent.java.commons.LocatorsLifecycle;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * Created by alpa on 3/3/20
 */
public class LocatorTestListener implements IInvokedMethodListener {

    private final LocatorsLifecycle locatorsLifecycle;

    public LocatorTestListener() {
        this.locatorsLifecycle = LocatorsLifecycle.getInstance();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            locatorsLifecycle.sendLocators(method.getTestMethod().getMethodName());
        }
    }
}
