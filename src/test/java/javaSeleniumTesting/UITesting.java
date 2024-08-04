package javaSeleniumTesting;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.openqa.selenium.chrome.ChromeOptions.*;

public class UITesting {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-search-engine-choice-screen");
        chromeOptions.addArguments(new String[]{"--incognito"});
        chromeOptions.addArguments(new String[]{"window-size=1980,1080"});
        chromeOptions.addArguments(new String[]{"--remote-allow-origins=*"});

        driver = new ChromeDriver(chromeOptions);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void sortLowToHighPrices() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        MainPageAfterLoginPOM mainPageAfterLogin = new MainPageAfterLoginPOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);
        mainPageAfterLogin.sortingPricesLowToHigh();
    }

    @Test
    public void sortHighToLowPrices() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        MainPageAfterLoginPOM mainPageAfterLoginPOM = new MainPageAfterLoginPOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);
        mainPageAfterLoginPOM.sortingPricesHighToLow();
    }
    @Test
    public void sortAToZNames() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        MainPageAfterLoginPOM mainPageAfterLoginPOM = new MainPageAfterLoginPOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);
        mainPageAfterLoginPOM.sortingNamesAToZ();
    }

    @Test
    public void sortZToANames() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        MainPageAfterLoginPOM mainPageAfterLoginPOM = new MainPageAfterLoginPOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);
        mainPageAfterLoginPOM.sortingNamesZToA();
    }
}
