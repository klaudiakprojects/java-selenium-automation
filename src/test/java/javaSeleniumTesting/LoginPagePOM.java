package javaSeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPagePOM {
    public LoginPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void goTo() {
        driver.get("https://www.saucedemo.com/");
    }

    public void loggingIn(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        String urlAfterLogin = driver.getCurrentUrl();
        Assert.assertTrue(urlAfterLogin.contains("inventory"));
    }

    public void loggingInWithIncorrectCredentials(String incorrectUsername, String incorrectPassword) {
        driver.findElement(By.id("user-name")).sendKeys(incorrectUsername);
        driver.findElement(By.id("password")).sendKeys(incorrectPassword);
        driver.findElement(By.id("login-button")).click();
        String urlAfterLogin = driver.getCurrentUrl();
        Assert.assertFalse(urlAfterLogin.contains("inventory"));
        WebElement errorMessage = driver.findElement(By.xpath("//h3[contains(text(), 'Username and password do not match any user in this service')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    public void loggingInWithIncorrectUsername(String incorrectUsername, String password) {
        driver.findElement(By.id("user-name")).sendKeys(incorrectUsername);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        String urlAfterLogin = driver.getCurrentUrl();
        Assert.assertFalse(urlAfterLogin.contains("inventory"));
        WebElement errorMessage = driver.findElement(By.xpath("//h3[contains(text(), 'Username and password do not match any user in this service')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    public void loggingInWithIncorrectPassword(String incorrectUsername, String password) {
        driver.findElement(By.id("user-name")).sendKeys(incorrectUsername);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        String urlAfterLogin = driver.getCurrentUrl();
        Assert.assertFalse(urlAfterLogin.contains("inventory"));
        WebElement errorMessage = driver.findElement(By.xpath("//h3[contains(text(), 'Username and password do not match any user in this service')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    public void loggingInWithoutAnyData() {
        driver.findElement(By.id("login-button")).click();
        String urlAfterLogin = driver.getCurrentUrl();
        Assert.assertFalse(urlAfterLogin.contains("inventory"));
        WebElement errorMessage = driver.findElement(By.xpath("//h3[contains(text(), 'Username is required')]"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }
}
