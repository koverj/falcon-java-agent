package io.koverj.agent.model;

/**
 * Created by alpa on 2/25/20
 */
public class Locator {

    private String uuid;
    private String url;
    private String subject;
    private String locator;
    private String parentUuid;

    public Locator(String uuid, String url, String subject, String locator, String parentUuid) {
        this.uuid = uuid;
        this.url = url;
        this.subject = subject;
        this.locator = locator;
        this.parentUuid = parentUuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getUrl() {
        return url;
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
                ", url='" + url + '\'' +
                ", subject='" + subject + '\'' +
                ", locator='" + locator + '\'' +
                ", parentUuid='" + parentUuid + '\'' +
                '}';
    }
}
