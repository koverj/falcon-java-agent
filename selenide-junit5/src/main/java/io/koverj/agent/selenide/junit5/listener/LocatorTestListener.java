package io.koverj.agent.selenide.junit5.listener;

import io.koverj.agent.java.commons.LocatorsLifecycle;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;


/**
 * Created by alpa on 2/25/20
 */
public class LocatorTestListener implements TestExecutionListener {

    private final LocatorsLifecycle locatorsLifecycle;

    public LocatorTestListener() {
        this.locatorsLifecycle = LocatorsLifecycle.getInstance();
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            locatorsLifecycle.sendLocators(testIdentifier.getDisplayName());
        }
    }
}
