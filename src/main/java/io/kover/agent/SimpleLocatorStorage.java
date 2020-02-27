<<<<<<< HEAD:src/main/java/io/koverj/agent/SimpleLocatorStorage.java
package io.koverj.agent;

import io.koverj.agent.model.Locator;
=======
package io.kover.agent;

import io.kover.agent.model.Locator;
>>>>>>> Move code to main dir for creation jar file:src/main/java/io/kover/agent/SimpleLocatorStorage.java

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
