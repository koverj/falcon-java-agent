package com.kover.tests.realapp.coverage.model;

/**
 * Created by alpa on 2/25/20
 */
public class Locator {

    private String uuid;
    private String subject;
    private String locator;
    private String parentUuid;

    public Locator(String uuid, String subject, String locator, String parentUuid) {
        this.uuid = uuid;
        this.subject = subject;
        this.locator = locator;
        this.parentUuid = parentUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSubject() {
        return subject;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    @Override
    public String toString() {
        return "Locator{" +
                "uuid='" + uuid + '\'' +
                ", subject='" + subject + '\'' +
                ", locator='" + locator + '\'' +
                ", parentUuid='" + parentUuid + '\'' +
                '}';
    }
}
