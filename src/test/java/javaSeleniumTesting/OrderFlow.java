package javaSeleniumTesting;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OrderFlow {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
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
    public void orderFlowTest() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";
        String firstItemName = "Sauce Labs Backpack";
        String firstItemPrice = "$29.99";
        String firstName = "Imie";
        String lastName = "Nazwisko";
        String postalCode = "00-100";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        MainPageAfterLoginPOM mainPage = new MainPageAfterLoginPOM(driver);
        ProductPagePOM productPage = new ProductPagePOM(driver);
        BasketPagePOM basketPage = new BasketPagePOM(driver);
        CheckoutPagePOM checkoutPage = new CheckoutPagePOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);
        mainPage.choosingFirstItem();
        productPage.addingItemToBasket();
        productPage.goToBasket();
        basketPage.verifyBasketWithOneProduct(firstItemName, firstItemPrice);
        basketPage.goToCheckout();
        checkoutPage.completeDetails(firstName, lastName, postalCode);
        checkoutPage.verifyProductDetails(firstItemName, firstItemPrice);
    }

    @Test
    public void deleteOneAndOnlyThingFromOrder() {

        //Given
        String username = "standard_user";
        String password = "secret_sauce";
        String firstItemName = "Sauce Labs Backpack";
        String firstItemPrice = "$29.99";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        MainPageAfterLoginPOM mainPage = new MainPageAfterLoginPOM(driver);
        ProductPagePOM productPage = new ProductPagePOM(driver);
        BasketPagePOM basketPage = new BasketPagePOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);
        mainPage.choosingFirstItem();
        productPage.addingItemToBasket();
        productPage.goToBasket();
        basketPage.verifyBasketWithOneProduct(firstItemName, firstItemPrice);
        basketPage.deleteOneAndOnlyThingFromOrder(firstItemName);
    }
@Test
    public void addTwoDifferentProductsToTheCart() {
        //Given
        String username = "standard_user";
        String password = "secret_sauce";
        String firstItemName = "Sauce Labs Backpack";
        String firstItemPrice = "$29.99";
        String secondItemName = "Sauce Labs Bike Light";
        String secondItemPrice = "$9.99";

        LoginPagePOM loginPage = new LoginPagePOM(driver);
        MainPageAfterLoginPOM mainPage = new MainPageAfterLoginPOM(driver);
        ProductPagePOM productPage = new ProductPagePOM(driver);
        BasketPagePOM basketPage = new BasketPagePOM(driver);

        loginPage.goTo();
        loginPage.loggingIn(username, password);
        mainPage.choosingFirstItem();
        productPage.addingItemToBasket();
        driver.navigate().back();
        mainPage.choosingSecondItem();
        productPage.addingItemToBasket();
        productPage.goToBasket();
        basketPage.verifyBasketWithTwoProducts(firstItemName, firstItemPrice, secondItemName, secondItemPrice);
    }
}
