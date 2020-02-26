package com.kover.tests.realapp.coverage.listener;

import com.kover.tests.realapp.coverage.model.LocatorResult;
import com.kover.tests.realapp.coverage.SimpleLocatorStorage;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;


/**
 * Created by alpa on 2/25/20
 */
public class LocatorTestListener implements TestExecutionListener {

    private final SimpleLocatorStorage storage;

    public LocatorTestListener() {
        this.storage = SimpleLocatorStorage.getInstance();
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            LocatorResult locatorResult = new LocatorResult(testIdentifier.getDisplayName(), storage.get());
            System.out.println(locatorResult);
            storage.clear();
        }
    }
}
