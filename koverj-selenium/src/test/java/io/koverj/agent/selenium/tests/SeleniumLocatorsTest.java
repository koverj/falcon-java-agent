package io.koverj.agent.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.koverj.agent.java.commons.LocatorsLifecycle;
import io.koverj.agent.selenium.LocatorsWatchingDriver;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;

public class SeleniumLocatorsTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("log.locators", "true");
        System.setProperty("use.koverj", "false");

        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();
        driver = new LocatorsWatchingDriver(chromeDriver);
        driver.get("https://angular.realworld.io/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    void testSeleniumLocators() {
        driver.get("https://angular.realworld.io/");

        driver.findElement(By.cssSelector("body > app-root > app-layout-header > nav > div > ul > li:nth-child(2) > a"));

        driver.findElement(By.cssSelector("body > app-root"))
                .findElement(By.cssSelector("app-layout-header > nav"));

        assertThat(LocatorsLifecycle.getInstance().getStorage().get().size(),
                CoreMatchers.equalTo(3));
    }
}
