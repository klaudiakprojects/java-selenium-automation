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
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
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

    @Test
    public void loginWithIncorrectUsername() {

        //Given
        String incorrectUsername = "new_user";
        String password = "secret_sauce";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.goTo();
        loginPage.loggingInWithIncorrectUsername(incorrectUsername, password);
    }

    @Test
    public void loginWithIncorrectPassword() {

        //Given
        String username = "standard_user";
        String incorrectPassword = "new_password";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.goTo();
        loginPage.loggingInWithIncorrectPassword(username, incorrectPassword);
    }

    @Test
    public void loginWithoutAnyData() {

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.goTo();
        loginPage.loggingInWithoutAnyData();
    }

    @Test
    public void loggingInAsALockedUser() {
        //Given
        String username = "locked_out_user";
        String password = "secret_sauce";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        loginPage.goTo();
        loginPage.loggingInAsALockedUser(username, password);
    }
}
