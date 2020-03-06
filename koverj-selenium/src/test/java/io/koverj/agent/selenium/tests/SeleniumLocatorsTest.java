package io.koverj.agent.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.koverj.agent.selenium.LocatorsWatchingDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumLocatorsTest {

    @Test
    void testSeleniumLocators() {
        System.setProperty("log.locators", "true");

        WebDriverManager.chromedriver().setup();
        WebDriver chromeDriver = new ChromeDriver();

        WebDriver driver = new LocatorsWatchingDriver(chromeDriver);

        driver.get("https://angular.realworld.io/");

        driver.findElement(By.cssSelector("body > app-root > app-layout-header > nav > div > ul > li:nth-child(2) > a"));

        driver.quit();
    }
}
