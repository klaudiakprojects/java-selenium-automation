package javaSeleniumTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ProductPagePOM {
    public ProductPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void addingItemToBasket() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    public void goToBasket() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }
}