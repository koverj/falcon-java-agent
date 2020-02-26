package com.kover.tests.realapp.coverage.model;

/**
 * Created by alpa on 2/25/20
 */
public class Locator {

    private String subject;
    private String locator;

    public Locator(String subject, String locator) {
        this.subject = subject;
        this.locator = locator;
    }

    public String getSubject() {
        return subject;
    }

    public String getLocator() {
        return locator;
    }


    @Override
    public String toString() {
        return "Locator{" +
                "subject='" + subject + '\'' +
                ", locator='" + locator + '\'' +
                '}';
    }
}
