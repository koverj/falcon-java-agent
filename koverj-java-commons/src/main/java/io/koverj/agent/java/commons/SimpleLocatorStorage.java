package io.koverj.agent.java.commons;

import io.koverj.agent.java.commons.model.Locator;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by alpa on 2/25/20
 */
public class SimpleLocatorStorage {

    private LinkedList<Locator> locators = new LinkedList<>();

    public LinkedList<Locator> get() {
        return locators;
    }

    public void put(Locator locator) {
        locators.add(locator);
    }

    public void clear() {
        locators.clear();
    }

}
