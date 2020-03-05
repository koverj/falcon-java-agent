package io.koverj.agent.java.commons.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alpa on 2/25/20
 */
public class LocatorResult {

    private String uuid;
    private String name;
    private LinkedList<Locator> locators = new LinkedList<>();

    public LocatorResult() {
    }

    public LocatorResult(String uuid, String name, LinkedList<Locator> locators) {
        this.uuid = uuid;
        this.name = name;
        this.locators = locators;
    }

    public LocatorResult setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public LocatorResult setName(String name) {
        this.name = name;
        return this;
    }

    public void setLocators(LinkedList<Locator> locators) {
        this.locators = locators;
    }

    public void add(Locator locators) {
        this.locators.add(locators);
    }

    public String getName() {
        return name;
    }

    public LinkedList<Locator> getLocators() {
        return locators;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "LocatorResult{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", locators=" + locators +
                '}';
    }
}
