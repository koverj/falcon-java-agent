package io.koverj.agent.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LocatorsWatchingDriver extends EventFiringWebDriver {
    public LocatorsWatchingDriver(WebDriver driver) {
        super(driver);
        register(new WebDriverLocatorListener());
    }
}
