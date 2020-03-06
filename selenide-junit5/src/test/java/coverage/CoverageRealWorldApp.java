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
import static com.codeborne.selenide.Selenide.$x;

public class CoverageRealWorldApp {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://angular.realworld.io";
        Selenide.open(Configuration.baseUrl);
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("LocatorEventsListener", new LocatorEventsListener());
        Selenide.open(Configuration.baseUrl);
    }

    @Test
    void testSimpleCssLocators() {
        $("[href='/login']").click();
        $("[formcontrolname='email']").setValue("sergio_89@ukr.net");
        $("[formcontrolname='password']").setValue("12345678");
        $("[type='submit']").click();
    }

    @Test
    void testSimpleXpathLocators() {
        $x("//*[@href='/login']").click();
        $x("//*[@formcontrolname='email']").setValue("sergio_89@ukr.net");
        $x("//*[@formcontrolname='password']").setValue("12345678");
        $x("//button[@type='submit']").click();
    }

    @Test
    void testRelativeCssLocators() {
        SelenideElement appRootElement = $("body > app-root");
        SelenideElement navBarElement = appRootElement.$("app-layout-header > nav");
        SelenideElement link = navBarElement.$("[href='/login']");
        link.click();
    }

    @Test
    void testRelativeXpathLocators() {
        SelenideElement appRootElement = $x("//body//app-root");
        SelenideElement navBarElement = appRootElement.$x(".//app-layout-header//nav");
        SelenideElement link = navBarElement.$x(".//*[@href='/login']");
        link.click();
    }

    @Test
    void testSimpleAndRelativeCssLocators() {
        $("[href='/login']").click();
        $("[formcontrolname='email']").setValue("sergio_89@ukr.net");
        $("[formcontrolname='password']").setValue("12345678");
        $("[type='submit']").click();

        SelenideElement appRootElement = $("body > app-root");
        SelenideElement navBarElement = appRootElement.$("app-layout-header > nav");
        SelenideElement link = navBarElement.$("[href='/login']");
        link.click();
    }

    @Test
    void testSimpleAndRelativeXpathLocators() {
        $x("//*[@href='/login']").click();
        $x("//*[@formcontrolname='email']").setValue("sergio_89@ukr.net");
        $x("//*[@formcontrolname='password']").setValue("12345678");
        $x("//button[@type='submit']").click();

        SelenideElement appRootElement = $x("//body//app-root");
        SelenideElement navBarElement = appRootElement.$x(".//app-layout-header//nav");
        SelenideElement link = navBarElement.$x(".//*[@href='/login']");
        link.click();
    }
}
