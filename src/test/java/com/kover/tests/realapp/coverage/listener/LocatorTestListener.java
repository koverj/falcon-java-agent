package com.kover.tests.realapp.coverage.listener;

import com.kover.tests.realapp.coverage.model.Locator;
import com.kover.tests.realapp.coverage.model.LocatorResult;
import com.kover.tests.realapp.coverage.SimpleLocatorStorage;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.List;
import java.util.Optional;


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
            processLocator(locatorResult);
            System.out.println(locatorResult);
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
}
