package com.kover.tests.realapp.coverage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.kover.agent.listener.LocatorEventsListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class CoverageRealWorldApp {

    static {
        SelenideLogger.addListener("LocatorEventsListener", new LocatorEventsListener());
    }

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://angular.realworld.io";
        Selenide.open(Configuration.baseUrl);
    }

    @Test
    void testSimpleLocators() {
        $("body > app-root > app-layout-header > nav > div > ul > li:nth-child(2) > a").click();
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(2) > input").setValue("sergio_89@ukr.net");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(3) > input").setValue("12345678");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > button").click();
    }

    @Test
    void testRelativeLocators() {
        SelenideElement appRootElement = $("body > app-root");
        SelenideElement navBarElement = appRootElement.$("app-layout-header > nav");
        SelenideElement link = navBarElement.$("div > ul > li:nth-child(2) > a");
        link.click();
    }

    @Test
    void testSimpleAndRelativeLocators() {
        $("body > app-root > app-layout-header > nav > div > ul > li:nth-child(2) > a").click();
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(2) > input").setValue("sergio_89@ukr.net");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(3) > input").setValue("12345678");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > button").click();

        Selenide.open(Configuration.baseUrl);

        SelenideElement appRootElement = $("body > app-root");
        SelenideElement navBarElement = appRootElement.$("app-layout-header > nav");
        SelenideElement link = navBarElement.$("div > ul > li:nth-child(2) > a");
        link.click();
    }
}
