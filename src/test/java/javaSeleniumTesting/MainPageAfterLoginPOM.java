package javaSeleniumTesting;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
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


    public void visabilityOfProductsOnTheMainPage() {
        List<WebElement> allProductsOnTheFirstPage = driver.findElements(By.className("inventory_item"));
        Assert.assertEquals(6, allProductsOnTheFirstPage.size());
    }

    public void choosingFirstItem() {

        driver.findElement(By.id("item_4_title_link")).click();
    }

    public void choosingSecondItem() {

        driver.findElement(By.id("item_0_title_link")).click();
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

        Collections.sort(pricesAsDouble, Collections.reverseOrder());

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
        assertArrayEquals(pricesAsDouble.toArray(), prices2.toArray());
    }

    public void sortingNamesAToZ() {
        List<WebElement> nameElementsBeforeSort = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_name')]"));
        List<String> namesBeforeSort = new ArrayList<>();

        for (WebElement nameElement : nameElementsBeforeSort) {
            String nameText = nameElement.getText();
         namesBeforeSort.add(nameText);
        }
       Collections.sort(namesBeforeSort);

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        selectElement.click();

        WebElement nameAToZ = driver.findElement(By.cssSelector("option[value='az']"));
        nameAToZ.click();

        List<WebElement> nameElementsAfterSort = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_name')]"));
        List<String> namesAfterSort = new ArrayList<>();

        for (WebElement nameElement : nameElementsAfterSort) {
            String nameText = nameElement.getText();
            namesAfterSort.add(nameText);
        }
        assertArrayEquals(namesBeforeSort.toArray(), namesAfterSort.toArray());
    }

    public void sortingNamesZToA() {
        List<WebElement> nameElementsBeforeSort = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_name')]"));
        List<String> namesBeforeSort = new ArrayList<>();

        for (WebElement nameElement : nameElementsBeforeSort) {
            String nameText = nameElement.getText();
            namesBeforeSort.add(nameText);
        }
      Collections.reverse(namesBeforeSort);

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        selectElement.click();

        WebElement nameZToA = driver.findElement(By.cssSelector("option[value='za']"));
        nameZToA.click();

        List<WebElement> nameElementsAfterSort = driver.findElements(By.xpath("//*[contains(@class, 'inventory_item_name')]"));
        List<String> namesAfterSort = new ArrayList<>();

        for (WebElement nameElement : nameElementsAfterSort) {
            String nameText = nameElement.getText();
            namesAfterSort.add(nameText);
        }
        assertArrayEquals(namesBeforeSort.toArray(), namesAfterSort.toArray());
    }
}
