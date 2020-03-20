package coverage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.koverj.agent.selenide.KoverjSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LocatorTypesTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://angular.realworld.io";
        Selenide.open(Configuration.baseUrl);
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("LocatorEventsListener", new KoverjSelenide());
        Selenide.open(Configuration.baseUrl);
    }

    @AfterEach
    void afterEach() {
        Selenide.close();
    }

    @Test
    public void testByCssSelectorLocator() {
        $(By.cssSelector("[href='/login']")).shouldBe(Condition.exist);
    }

    @Test
    public void testByCssSelectorSelenideLocator() {
        $(byCssSelector("[href='/login']")).shouldBe(Condition.exist);
    }

    @Test
    public void testByXpathLocator() {
        $(By.xpath("//*[@href='/login']")).shouldBe(Condition.exist);
    }

    @Test
    public void testByXpathSelenideLocator() {
        $(byXpath("//*[@href='/login']")).shouldBe(Condition.exist);
    }

    @Test
    public void testByXpathSelenide2Locator() {
        $x("//*[@href='/login']").shouldBe(Condition.exist);
    }

    @Test
    void testByIdLocator() {
        $(byId("id")).shouldBe(Condition.exist);
    }

    @Test
    void testByNameLocator() {
        $(byName("name")).shouldBe(Condition.exist);
    }

    @Test
    void testByAttributeLocator() {
        $(byAttribute("routerlink", "/login")).shouldBe(Condition.exist);
    }

    @Test
    void testByClassNameLocator() {
        $(byClassName("navbar-brand")).shouldBe(Condition.exist);
    }

    @Test
    void testByLinkTextLocator() {
        $(byLinkText("Home")).shouldBe(Condition.exist);
    }

    @Test
    void testByPartialLinkTextLocator() {
        $(byPartialLinkText("Sign")).shouldBe(Condition.exist);
    }

    @Test
    void testByTitleLocator() {
        $(byTitle("title")).shouldBe(Condition.exist);
    }

    @Test
    void testByValueLocator() {
        $(byValue("value")).shouldBe(Condition.exist);
    }

    @Test
    void testByNameSeleniumLocator() {
        $(By.name("byName")).shouldBe(Condition.exist);
    }

    @Test
    void testByTagNameLocator() {
        $(By.tagName("div")).shouldBe(Condition.exist);
    }
}
