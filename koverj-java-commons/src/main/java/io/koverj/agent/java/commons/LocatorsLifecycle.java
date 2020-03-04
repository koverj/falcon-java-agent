package io.koverj.agent.java.commons;

import io.koverj.agent.java.commons.config.KoverjConfig;
import io.koverj.agent.java.commons.model.Locator;
import io.koverj.agent.java.commons.model.LocatorResult;

import java.util.List;

public class LocatorsLifecycle {

    private final SimpleLocatorStorage storage;
    private final KoverjClient koverjClient;

    public LocatorsLifecycle() {
        this.storage = SimpleLocatorStorage.getInstance();
        this.koverjClient = new KoverjClient();
    }

    public void sendLocators(String testName){
        LocatorResult locatorResult = new LocatorResult(testName, storage.get());
        processLocator(locatorResult);

        if (KoverjConfig.isSendToKover) {
            koverjClient.sendLocatorsResult(locatorResult);
        }
        storage.clear();
    }

    public void processLocator(LocatorResult locatorResult) {
        List<Locator> locators = locatorResult.getLocators();
        locators.forEach(locator -> {
            String parentUuid = locator.getParentUuid();
            if (parentUuid != null) {
                locators.stream()
                        .filter(parentLocator -> parentLocator.getUuid().equalsIgnoreCase(parentUuid))
                        .findFirst()
                        .ifPresent(value -> locator.setLocator(value.getLocator() + " " + locator.getLocator()));
            }
        });
    }
}
