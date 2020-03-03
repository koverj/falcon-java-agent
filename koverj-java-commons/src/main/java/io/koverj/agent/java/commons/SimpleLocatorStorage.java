package io.koverj.agent.java.commons;

import io.koverj.agent.java.commons.model.Locator;
import io.koverj.agent.java.commons.model.LocatorResult;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by alpa on 2/25/20
 */
public class SimpleLocatorStorage {

    private final LinkedList<Locator> storage = new LinkedList<>();

    private static SimpleLocatorStorage instance;

    public static SimpleLocatorStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SimpleLocatorStorage();
        }
        return instance;
    }

    public LinkedList<Locator> get() {
        return storage;
    }

    public void put(Locator locator) {
        storage.add(locator);
    }

    public void clear() {
        storage.clear();
    }

    public void processLocator(LocatorResult locatorResult) {
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
