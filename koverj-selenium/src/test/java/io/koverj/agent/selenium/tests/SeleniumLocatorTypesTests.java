package io.koverj.agent.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.koverj.agent.selenium.LocatorsWatchingDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumLocatorTypesTests {

    private WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("log.locators", "true");
        System.setProperty("use.koverj", "false");

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach() {
        WebDriver chromeDriver = new ChromeDriver();
        driver = new LocatorsWatchingDriver(chromeDriver);
        driver.get("https://angular.realworld.io/");
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    public void testByCssSelectorLocator() {
        driver.findElement(By.cssSelector("[href='/login']"));
    }

    @Test
    public void testByXpathLocator() {
        driver.findElement(By.xpath("//*[@href='/login']"));
    }

    @Test
    void testByIdLocator() {
        driver.findElement(By.id("id"));
    }

    @Test
    void testByNameLocator() {
        driver.findElement(By.name("name"));
    }

    @Test
    void testByClassNameLocator() {
        driver.findElement(By.className("navbar-brand"));
    }

    @Test
    void testByLinkTextLocator() {
        driver.findElement(By.linkText("Home"));
    }

    @Test
    void testByPartialLinkTextLocator() {
        driver.findElement(By.partialLinkText("Sign"));
    }

    @Test
    void testByTagNameLocator() {
        driver.findElement(By.tagName("div"));
    }
}
