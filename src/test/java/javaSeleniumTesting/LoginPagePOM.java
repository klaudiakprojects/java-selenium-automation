package javaSeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPagePOM {
    public LoginPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void loggingIn(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        String urlAfterLogin = driver.getCurrentUrl();
        Assert.assertTrue(urlAfterLogin.contains("inventory"));
    }
}
