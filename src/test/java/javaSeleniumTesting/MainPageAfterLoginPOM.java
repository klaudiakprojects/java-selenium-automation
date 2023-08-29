package javaSeleniumTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class MainPageAfterLoginPOM {
    public MainPageAfterLoginPOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void choosingItem() {
        driver.findElement(By.id("item_4_title_link")).click();

    }
}