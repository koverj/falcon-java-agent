package io.koverj.agent.java.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.koverj.agent.java.commons.config.KoverjConfig;
import io.koverj.agent.java.commons.model.Locator;
import io.koverj.agent.java.commons.model.LocatorResult;

import java.util.List;
import java.util.Objects;

public class LocatorsLifecycle {

    private final ThreadLocal<SimpleLocatorStorage> storage = ThreadLocal.withInitial(SimpleLocatorStorage::new);
    private final KoverjClient koverjClient;

    private static LocatorsLifecycle instance;

    private LocatorsLifecycle() {
        this.koverjClient = new KoverjClient();
    }

    public static LocatorsLifecycle getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LocatorsLifecycle();
        }
        return instance;
    }

    public void sendLocators(String testName){
        LocatorResult locatorResult = new LocatorResult(testName, getStorage().get());
        processLocator(locatorResult);

        if (KoverjConfig.logLocators) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(locatorResult));
        }

        if (KoverjConfig.isSendToKover) {
            koverjClient.sendLocatorsResult(locatorResult);
        }
        getStorage().clear();
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

    public SimpleLocatorStorage getStorage() {
        return storage.get();
    }
}
