package io.koverj.agent.selenide.junit5.listener;

import io.koverj.agent.java.commons.LocatorsLifecycle;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.UUID;


/**
 * Created by alpa on 2/25/20
 */
public class KoverjListener implements TestExecutionListener {

    private final LocatorsLifecycle locatorsLifecycle;

    private final static String BUILD_ID = System.getProperty("koverj.build.name", UUID.randomUUID().toString());

    public KoverjListener() {
        this.locatorsLifecycle = LocatorsLifecycle.getInstance();
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            locatorsLifecycle.sendLocators(BUILD_ID, testIdentifier.getDisplayName());
        }
    }
}
