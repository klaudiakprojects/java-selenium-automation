package javaSeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class CheckoutPagePOM {
    public CheckoutPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void completeDetails(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        driver.findElement(By.id("continue")).click();
    }
    public void verifyProductDetails(String firstItemName, String firstItemPrice) {
        String checkoutItemName = driver.findElement(By.className("inventory_item_name")).getText();
        String checkoutItemPrice = driver.findElement(By.className("inventory_item_price")).getText();
        Assert.assertEquals(firstItemName, checkoutItemName);
        Assert.assertEquals(firstItemPrice, checkoutItemPrice);
    }
}