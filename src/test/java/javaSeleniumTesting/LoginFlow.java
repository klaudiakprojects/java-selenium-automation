package javaSeleniumTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class LoginFlow {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
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
    public void loginWithValidCredentials() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";
        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.goTo();
        loginPage.loggingIn(username, password);
    }

    @Test
    public void loginWithIncorrectCredentials() {

        //Given
        String incorrectUsername = "new_user";
        String incorrectPassword = "new_password";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.goTo();
        loginPage.loggingInWithIncorrectCredentials(incorrectUsername, incorrectPassword);
    }
}
