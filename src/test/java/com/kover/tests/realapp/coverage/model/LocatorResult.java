package com.kover.tests.realapp.coverage.model;

import java.util.List;

/**
 * Created by alpa on 2/25/20
 */
public class LocatorResult {

    private String name;
    private List<Locator> locators;


    public LocatorResult(String name, List<Locator> locators) {
        this.name = name;
        this.locators = locators;
    }

    public String getName() {
        return name;
    }

    public List<Locator> getLocators() {
        return locators;
    }

    @Override
    public String toString() {
        return "LocatorResult{" +
                "name=" + name +
                ", locators=" + locators +
                '}';
    }
}
