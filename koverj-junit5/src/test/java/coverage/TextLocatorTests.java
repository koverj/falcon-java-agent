package coverage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.koverj.agent.selenide.KoverjSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TextLocatorTests {

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

    @Test
    void testSimpleByTextLocator() {
        $(byText("Home")).shouldBe(Condition.exist);
        $(byText("Sign in")).shouldBe(Condition.exist);
        $(byText("Sign up")).shouldBe(Condition.exist);
    }

    @Test
    void testSimpleWithTextLocator() {
        $(withText("Home")).shouldBe(Condition.exist);
        $(withText("Sign in")).shouldBe(Condition.exist);
        $(withText("Sign up")).shouldBe(Condition.exist);
    }

}
