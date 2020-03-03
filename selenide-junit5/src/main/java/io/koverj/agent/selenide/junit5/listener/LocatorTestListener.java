package io.koverj.agent.selenide.junit5.listener;

import io.koverj.agent.java.commons.KoverjClient;
import io.koverj.agent.java.commons.SimpleLocatorStorage;
import io.koverj.agent.java.commons.config.KoverjConfig;
import io.koverj.agent.java.commons.model.LocatorResult;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;


/**
 * Created by alpa on 2/25/20
 */
public class LocatorTestListener implements TestExecutionListener {

    private final SimpleLocatorStorage storage;
    private final KoverjClient koverjClient;

    public LocatorTestListener() {
        this.storage = SimpleLocatorStorage.getInstance();
        this.koverjClient = new KoverjClient();
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            LocatorResult locatorResult = new LocatorResult(testIdentifier.getDisplayName(), storage.get());
            storage.processLocator(locatorResult);
            System.out.println(locatorResult);
            if (KoverjConfig.isSendToKover) {
                koverjClient.sendLocatorsResult(locatorResult);
            }
            storage.clear();
        }
    }

}
