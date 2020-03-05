package io.koverj.agent.java.commons;

import io.koverj.agent.java.commons.model.Locator;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by alpa on 2/25/20
 */
public class SimpleLocatorStorage {

    private final Context storage = new Context();

    private static SimpleLocatorStorage instance;

    public static SimpleLocatorStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SimpleLocatorStorage();
        }
        return instance;
    }

    public LinkedList<Locator> get() {
        return storage.get();
    }

    public void put(Locator locator) {
        get().add(locator);
    }

    public void clear() {
        get().clear();
    }

    private static class Context extends ThreadLocal<LinkedList<Locator>> {

        @Override
        protected LinkedList<Locator> initialValue() {
            return new LinkedList<>();
        }
    }


}
