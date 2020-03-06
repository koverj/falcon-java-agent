package io.koverj.agent.selenium;

import io.koverj.agent.java.commons.LocatorsLifecycle;
import io.koverj.agent.java.commons.model.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.UUID;

public class WebDriverLocatorListener extends LocatorEventListener {

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
                .build();

        lifecycle.getStorage().put(locator);
    }
}
