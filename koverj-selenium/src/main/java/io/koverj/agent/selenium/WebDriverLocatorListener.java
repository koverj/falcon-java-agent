package io.koverj.agent.selenium;

import io.koverj.agent.java.commons.LocatorsLifecycle;
import io.koverj.agent.java.commons.model.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.UUID;

public class WebDriverLocatorListener extends LocatorEventListener {

    private static final String HAS_PARENT_ELEMENT = "Has parent element";
    private final LocatorsLifecycle lifecycle;

    public WebDriverLocatorListener() {
        this.lifecycle = LocatorsLifecycle.getInstance();
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        String currentUrl = driver.getCurrentUrl();
        String uuid = UUID.randomUUID().toString();

        Locator locator = Locator.builder()
                .uuid(uuid)
                .locator(by.toString())
                .url(currentUrl)
                .subject(getSubject(element))
                .build();

        System.out.println(locator);

        LinkedList<Locator> locators = lifecycle.getStorage().get();
        if (!locators.isEmpty()) {
            Locator previousLocator = locators.getLast();
            if (previousLocator != null) {
                if (locator.getSubject() != null) {
                    String parentUuid = previousLocator.getUuid();
                    locator.setParentUuid(parentUuid);
                    lifecycle.getStorage().put(locator);
                } else {
                    lifecycle.getStorage().put(locator);
                }
            }
        } else {
            lifecycle.getStorage().put(locator);
        }
    }

    private String getSubject(WebElement element) {
        return element == null ? null : HAS_PARENT_ELEMENT;
    }
}
