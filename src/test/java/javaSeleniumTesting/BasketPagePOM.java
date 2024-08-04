package javaSeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasketPagePOM {
    public BasketPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    public void verifyBasketWithOneProduct(String firstItemName, String firstItemPrice) {
        String cartUrl = driver.getCurrentUrl();
        Assert.assertTrue(cartUrl.contains("cart"));

        List<WebElement> cartItemNames = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> cartItemPrices = driver.findElements(By.className("inventory_item_price"));
        String firstCartItemName = cartItemNames.get(0).getText();
        String firstCartItemPrice = cartItemPrices.get(0).getText();
        Assert.assertEquals(firstItemName, firstCartItemName);
        Assert.assertEquals(firstItemPrice, firstCartItemPrice);
    }

    public void verifyBasketWithTwoProducts(String firstItemName, String firstItemPrice, String secondItemName, String secondItemPrice) {
        String cartUrl = driver.getCurrentUrl();
        Assert.assertTrue(cartUrl.contains("cart"));

        List<WebElement> cartItemNames = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> cartItemPrices = driver.findElements(By.className("inventory_item_price"));
        String firstCartItemName = cartItemNames.get(0).getText();
        String firstCartItemPrice = cartItemPrices.get(0).getText();
        Assert.assertEquals(firstItemName, firstCartItemName);
        Assert.assertEquals(firstItemPrice, firstCartItemPrice);
        String secondCartItemName = cartItemNames.get(1).getText();
        String secondCartItemPrice = cartItemPrices.get(1).getText();
        Assert.assertEquals(secondItemName, secondCartItemName);
        Assert.assertEquals(secondItemPrice, secondCartItemPrice);
    }

    public void goToCheckout() {

        driver.findElement(By.id("checkout")).click();
    }

    public void deleteOneAndOnlyThingFromOrder(String firstItemName) {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        boolean backpackExistence = driver.findElements(By.xpath("//*[contains(text(), '" + firstItemName + "')]")).size() > 0;
        Assert.assertTrue(!backpackExistence);
    }
}
