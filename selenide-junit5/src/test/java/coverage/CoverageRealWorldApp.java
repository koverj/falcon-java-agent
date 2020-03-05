package coverage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.koverj.agent.selenide.LocatorEventsListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class CoverageRealWorldApp {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://angular.realworld.io";
        Selenide.open(Configuration.baseUrl);
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("LocatorEventsListener", new LocatorEventsListener());
    }

    @Test
    void testSimpleLocators() {
        Selenide.open(Configuration.baseUrl);

        $("body > app-root > app-layout-header > nav > div > ul > li:nth-child(2) > a").click();
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(2) > input").setValue("sergio_89@ukr.net");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(3) > input").setValue("12345678");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > button").click();
    }

    @Test
    void testRelativeLocators() {
        Selenide.open(Configuration.baseUrl);

        SelenideElement appRootElement = $("body > app-root");
        SelenideElement navBarElement = appRootElement.$("app-layout-header > nav");
        SelenideElement link = navBarElement.$("div > ul > li:nth-child(2) > a");
        link.click();
    }

    @Test
    void testSimpleAndRelativeLocators() {
        Selenide.open(Configuration.baseUrl);

        $("body > app-root > app-layout-header > nav > div > ul > li:nth-child(2) > a").click();
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(2) > input").setValue("sergio_89@ukr.net");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > fieldset:nth-child(3) > input").setValue("12345678");
        $("body > app-root > app-auth-page > div > div > div > div > form > fieldset > button").click();



        SelenideElement appRootElement = $("body > app-root");
        SelenideElement navBarElement = appRootElement.$("app-layout-header > nav");
        SelenideElement link = navBarElement.$("div > ul > li:nth-child(2) > a");
        link.click();
    }
}
