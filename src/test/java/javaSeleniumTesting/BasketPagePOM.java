package javaSeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class BasketPagePOM {
    public BasketPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void verifyBasket(String firstItemName, String firstItemPrice) {
        String cartUrl = driver.getCurrentUrl();
        Assert.assertTrue(cartUrl.contains("cart"));
        String cartItemName = driver.findElement(By.id("item_4_title_link")).getText();
        String cartItemPrice = driver.findElement(By.className("inventory_item_price")).getText();
        Assert.assertEquals(firstItemName, cartItemName);
        Assert.assertEquals(firstItemPrice, cartItemPrice);
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
