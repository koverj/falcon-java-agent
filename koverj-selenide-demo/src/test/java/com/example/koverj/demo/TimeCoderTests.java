package com.example.koverj.demo;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.koverj.agent.selenide.KoverjSelenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TimeCoderTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "http://localhost:8087";
        Selenide.open("/");
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("LocatorEventsListener", new KoverjSelenide());
    }

    @Test
    @Tag("smoke")
    void testCanNavigateHeader() {
        login();
        $("#navbarTogglerDemo01 > ul > li:nth-child(1) > button").click();
        $("#navbarTogglerDemo01 > ul > li:nth-child(2) > a").click();
        $("#navbarTogglerDemo01 > ul > li:nth-child(3) > button").click();
        $("#navbarTogglerDemo01 > ul > li:nth-child(4) > button").click();
    }

    private void login() {
        Selenide.open("/login");
        $("#usernameOrEmail").setValue("demo");
        $("#password").sendKeys("123456");
        $("body > app-root > app-layout > div > app-login > div > div > form > div:nth-child(3) > button").click();
    }

    @Test
    @Tag("smoke")
    void testCanLogin() {
        login();
        $("#navbarTogglerDemo01 > button").shouldHave(text("Logout"));
    }

    @Test
    @Tag("smoke")
    void testCanNotLoginWithWrongCredentials() {
        Selenide.open("/login");
        $("#usernameOrEmail").setValue("demo");
        $("#password").sendKeys("A");
        $("body > app-root > app-layout > div > app-login > div > div > form > div:nth-child(3) > button").click();
    }

    @Test
    @Tag("smoke")
    void testCanAddEpisode() {
        login();
        $("#navbarTogglerDemo01 > button").shouldHave(text("Logout"));
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
    }

    @Test
    @Tag("smoke")
    void testCanRemoveEpisode() {
        login();
        $("#navbarTogglerDemo01 > button").shouldHave(text("Logout"));
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li > a").click();
        $("#mat-dialog-0 > app-confirmation-dialog > div.mat-dialog-actions > button.btn.btn-warning.btn-block.mat-button").click();
    }

    @Test
    @Tag("smoke")
    void testCanAddFreeTheme() {
        Selenide.open("/add-theme");
        $("#themeTitle").setValue("This is free theme");
        $("body > app-root > app-layout > div > app-propose-theme > div:nth-child(1) > div.col-8.mb-2 > form > button").click();
    }

    @Test
    @Tag("smoke")
    void testCanOpenEpisodeDetailsAddThemeAndStartEpisode() {
        Selenide.open("/add-theme");
        $("#themeTitle").setValue("This is free theme");
        $("body > app-root > app-layout > div > app-propose-theme > div:nth-child(1) > div.col-8.mb-2 > form > button").click();
        login();
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li:nth-child(1) > span").click();
        $("#themeTitle").setValue("1").pressEnter();
        $("body > app-root > app-layout > div > app-episode-details > div > div:nth-child(1) > div > button").click();
    }

    @Test
    @Tag("smoke")
    void testCanStartEpisode() {
        login();
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li:nth-child(1) > span").click();
        $("#startEpisode").click();
        $("#mat-dialog-0 > app-confirmation-dialog > div.mat-dialog-actions > button.btn.btn-warning.btn-block.mat-button").click();
        $("#stopEpisode").click();
    }

    @Test
    @Tag("smoke")
    void testCanEditEpisodeTheme() {
        login();
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li:nth-child(1) > span").click();
        $("#themeTitle").setValue("1").pressEnter();
        $("#editTheme").hover().click();
        $("#updateThemeTitle").setValue("124");
        $("body > app-root > app-layout > div > app-episode-details > div > div.edit-navbar > div > div > form > button.btn.btn-success.btn-block").click();
    }

    @Test
    @Tag("smoke")
    void testCanCancelEditEpisodeTheme() {
        login();
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li:nth-child(1) > span").click();
        $("#themeTitle").setValue("1").pressEnter();
        $("#editTheme").hover().click();
        $("#updateThemeTitle").setValue("124");
        $("body > app-root > app-layout > div > app-episode-details > div > div.edit-navbar > div > div > form > button.btn.btn-secondary.btn-block").click();
    }

    @Test
    @Tag("smoke")
    void testCanAddLinkToTheme() {
        login();
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li:nth-child(1) > span").click();
        $("#themeTitle").setValue("[http://localhost:9090](demo)").pressEnter();
    }

    @Test
    @Tag("smoke")
    void testCanUnlinkThemeFromEpisode() {
        login();
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li:nth-child(1) > span").click();
        $("#themeTitle").setValue("[http://localhost:9090](demo)").pressEnter();
        $("#unlinkTheme").click();
    }

    @Test
    @Tag("smoke")
    void testCanDeleteFreeTheme() {
        Selenide.open("/add-theme");
        $("#themeTitle").setValue("This is free theme");
        $("body > app-root > app-layout > div > app-propose-theme > div:nth-child(1) > div.col-8.mb-2 > form > button").click();
        login();
        $("#navbarTogglerDemo01 > ul > li:nth-child(4) > button").click();
        $("#deleteFreeTheme").click();
        $("#mat-dialog-0 > app-confirmation-dialog > div.mat-dialog-actions > button.btn.btn-warning.btn-block.mat-button").click();
    }

    @Test
    @Tag("smoke")
    void testCanLogout() {
        login();
        $("#navbarTogglerDemo01 > button").shouldHave(text("Logout"));
        $("#navbarTogglerDemo01 > button").click();
    }

    @Test
    @Tag("smoke")
    void testCanLinkThemeToPost() {
        Selenide.open("/add-theme");
        $("#themeTitle").setValue("This is free theme");
        $("body > app-root > app-layout > div > app-propose-theme > div:nth-child(1) > div.col-8.mb-2 > form > button").click();
        login();
        $("#episodeName").setValue("#1" + RandomStringUtils.randomAlphabetic(3));
        $("body > app-root > app-layout > div > app-episode-list > div > div > form > div > div.col-4 > button").click();
        $("body > app-root > app-layout > div > app-episode-list > div > div > ul > li:nth-child(1) > span").click();
        $("body > app-root > app-layout > div > app-episode-details > div > div:nth-child(1) > div > button").click();
        $("#defaultCheck0").click();
        $("body > app-root > app-layout > div > app-link-themes > div > div > form > button").click();
    }
}
