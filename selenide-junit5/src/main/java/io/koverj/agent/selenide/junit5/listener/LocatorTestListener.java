package io.koverj.agent.selenide.junit5.listener;

import io.koverj.agent.java.commons.LocatorsLifecycle;
import io.koverj.agent.java.commons.model.LocatorResult;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import java.util.Optional;

/**
 * Created by alpa on 2/25/20
 */
public class LocatorTestListener implements TestExecutionListener {

    private final LocatorsLifecycle locatorsLifecycle;

    public LocatorTestListener() {
        this.locatorsLifecycle = LocatorsLifecycle.getInstance();
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            startTestContainer(testIdentifier);
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            stopTestContainer(testIdentifier);
        }
    }

    private void startTestContainer(final TestIdentifier testIdentifier) {
        final String uuid = locatorsLifecycle.getContainers().getOrCreate(testIdentifier);
        final LocatorResult result = new LocatorResult()
                .setUuid(uuid)
                .setName(testIdentifier.getDisplayName());

        locatorsLifecycle.startTestContainer(result);
    }

    private void stopTestContainer(final TestIdentifier testIdentifier) {
        final Optional<String> maybeUuid = locatorsLifecycle.getContainers().get(testIdentifier);
        if (!maybeUuid.isPresent()) {
            return;
        }
        final String uuid = maybeUuid.get();

        locatorsLifecycle.sendLocators(uuid);
        locatorsLifecycle.stopTestContainer(uuid);
    }

}
