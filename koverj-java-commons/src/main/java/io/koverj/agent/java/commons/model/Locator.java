package io.koverj.agent.java.commons.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by alpa on 2/25/20
 */
@Builder
@Getter
@Setter
@ToString
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
}
