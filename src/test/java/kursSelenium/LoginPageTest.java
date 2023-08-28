package kursSelenium;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPageTest {
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
    public void homeTest() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";
        String firstItemName = "Sauce Labs Backpack";
        String firstItemPrice = "$29.99";
        String firstName = "Imie";
        String lastName = "Nazwisko";
        String postalCode = "00-100";

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        String urlAfterLogin = driver.getCurrentUrl();
        Assert.assertTrue(urlAfterLogin.contains("inventory"));
        driver.findElement(By.id("item_4_title_link")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        String cartUrl = driver.getCurrentUrl();
        Assert.assertTrue(cartUrl.contains("cart"));
        String cartItemName = driver.findElement(By.id("item_4_title_link")).getText();
        String cartItemPrice = driver.findElement(By.className("inventory_item_price")).getText();
        Assert.assertEquals(firstItemName, cartItemName);
        Assert.assertEquals(firstItemPrice, cartItemPrice);
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        driver.findElement(By.id("continue")).click();
        String checkoutItemName = driver.findElement(By.className("inventory_item_name")).getText();
        String checkoutItemPrice = driver.findElement(By.className("inventory_item_price")).getText();
        Assert.assertEquals(firstItemName, checkoutItemName);
        Assert.assertEquals(firstItemPrice, checkoutItemPrice);
    }
}
