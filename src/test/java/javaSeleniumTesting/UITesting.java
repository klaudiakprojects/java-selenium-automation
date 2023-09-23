package javaSeleniumTesting;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class UITesting {
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
    public void UITest() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";

        LoginPagePOM loginPage = new LoginPagePOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);

        List<WebElement> priceElementsBeforeSort = driver.findElements(By.className("inventory_item_price"));
        List<String> pricesBeforeSort = new ArrayList<>();

        for (WebElement priceElement : priceElementsBeforeSort) {
            String priceText = priceElement.getText();
            String regexPattern = "[0-9.]+";
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(priceText);

            if (matcher.find()) {
                String price = matcher.group();
                pricesBeforeSort.add(price);
            }
        }
        List<Double> pricesAsDouble = new ArrayList<>();
        for (String priceString : pricesBeforeSort) {
            double priceDouble = Double.parseDouble(priceString);
            pricesAsDouble.add(priceDouble);
        }

        Collections.sort(pricesAsDouble);

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        selectElement.click();

        WebElement priceLowToHigh = driver.findElement(By.cssSelector("option[value='lohi']"));
        priceLowToHigh.click();

        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            String regexPattern = "[0-9.]+";
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(priceText);

            if (matcher.find()) {
                String price = matcher.group();
                prices.add(Double.parseDouble(price));
            }
        }
        assertArrayEquals(pricesAsDouble.toArray(), prices.toArray());
          }

}
