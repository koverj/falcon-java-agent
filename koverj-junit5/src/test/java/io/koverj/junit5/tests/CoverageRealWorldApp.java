package io.koverj.junit5.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.koverj.agent.java.commons.LocatorsLifecycle;
import io.koverj.agent.java.commons.config.KoverjConfig;
import io.koverj.agent.java.commons.model.Locator;
import io.koverj.agent.selenide.KoverjSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class CoverageRealWorldApp {

    @BeforeAll
    static void setUp() {
        KoverjConfig.isSendToKover = false;
        KoverjConfig.logLocators = true;

        Configuration.baseUrl = "https://angular.realworld.io";
        Selenide.open(Configuration.baseUrl);
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("LocatorEventsListener", new KoverjSelenide());
        Selenide.open(Configuration.baseUrl);
    }

    @Test
    void testSimpleCssLocators() {
        $("[href='/login']").click();
        $("[formcontrolname='email']").setValue("sergio_89@ukr.net");
        $("[formcontrolname='password']").setValue("12345678");
        $("[type='submit']").click();

        assertThat(getLocatorStorage().size()).isEqualTo(4);
        assertThat(getLocatorsList())
                .isEqualTo(Arrays.asList(
                        "[href='/login']",
                        "[formcontrolname='email']",
                        "[formcontrolname='password']",
                        "[type='submit']"));
    }

    @Test
    void testSimpleXpathLocators() {
        $x("//*[@href='/login']").click();
        $x("//*[@formcontrolname='email']").setValue("sergio_89@ukr.net");
        $x("//*[@formcontrolname='password']").setValue("12345678");
        $x("//button[@type='submit']").click();

        assertThat(getLocatorStorage().size()).isEqualTo(4);
        assertThat(getLocatorsList())
                .isEqualTo(Arrays.asList(
                        "By.xpath: //*[@href='/login']",
                        "By.xpath: //*[@formcontrolname='email']",
                        "By.xpath: //*[@formcontrolname='password']",
                        "By.xpath: //button[@type='submit']"));
    }

    @Test
    void testRelativeCssLocators() {
        SelenideElement appRootElement = $("body > app-root");
        SelenideElement navBarElement = appRootElement.$("app-layout-header > nav");
        SelenideElement link = navBarElement.$("[href='/login']");
        link.click();

        assertThat(getLocatorStorage().size()).isEqualTo(3);
    }

    @Test
    void testRelativeXpathLocators() {
        SelenideElement appRootElement = $x("//body//app-root");
        SelenideElement navBarElement = appRootElement.$x(".//app-layout-header//nav");
        SelenideElement link = navBarElement.$x(".//*[@href='/login']");
        link.click();

        assertThat(getLocatorStorage().size()).isEqualTo(3);
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

        assertThat(getLocatorStorage().size()).isEqualTo(7);
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

        assertThat(getLocatorStorage().size()).isEqualTo(7);
    }

    private LinkedList<Locator> getLocatorStorage() {
        return LocatorsLifecycle.getInstance().getStorage().get();
    }

    private List<String> getLocatorsList() {
        return getLocatorStorage().stream().map(Locator::getLocator).collect(Collectors.toList());
    }
}
