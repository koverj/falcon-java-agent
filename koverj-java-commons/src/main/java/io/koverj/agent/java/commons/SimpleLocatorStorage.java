package io.koverj.agent.java.commons;

import io.koverj.agent.java.commons.model.Locator;

import java.util.LinkedList;
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

}
