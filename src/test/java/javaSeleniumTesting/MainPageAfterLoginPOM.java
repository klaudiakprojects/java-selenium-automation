package javaSeleniumTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MainPageAfterLoginPOM {
    public MainPageAfterLoginPOM(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public void choosingItem() {
        driver.findElement(By.id("item_4_title_link")).click();
    }

    public void sortingPricesLowToHigh() {
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

    public void sortingPricesHighToLow() {
        List<WebElement> priceElementsBeforeSort2 = driver.findElements(By.className("inventory_item_price"));
        List<String> pricesBeforeSort2 = new ArrayList<>();

        for (WebElement priceElement : priceElementsBeforeSort2) {
            String priceText = priceElement.getText();
            String regexPattern = "[0-9.]+";
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(priceText);

            if (matcher.find()) {
                String price = matcher.group();
                pricesBeforeSort2.add(price);
            }
        }
        List<Double> pricesAsDouble2 = new ArrayList<>();
        for (String priceString : pricesBeforeSort2) {
            double priceDouble = Double.parseDouble(priceString);
            pricesAsDouble2.add(priceDouble);
        }

        Collections.sort(pricesAsDouble2, Collections.reverseOrder());

        WebElement selectElement2 = driver.findElement(By.className("product_sort_container"));
        selectElement2.click();

        WebElement priceHighToLow = driver.findElement(By.cssSelector("option[value='hilo']"));
        priceHighToLow.click();

        List<WebElement> priceElements2 = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices2 = new ArrayList<>();

        for (WebElement priceElement : priceElements2) {
            String priceText = priceElement.getText();
            String regexPattern = "[0-9.]+";
            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(priceText);

            if (matcher.find()) {
                String price = matcher.group();
                prices2.add(Double.parseDouble(price));
            }
        }
        assertArrayEquals(pricesAsDouble2.toArray(), prices2.toArray());
    }
}
