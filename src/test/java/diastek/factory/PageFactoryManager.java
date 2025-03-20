package diastek.factory;

import diastek.pages.*;
import org.openqa.selenium.WebDriver;

public class PageFactoryManager {
    private static HomePage homePage;
    private static CartPage cartPage;
    private static ProductPage productPage;
    private static ProductDetailPage productDetailPage;

    public static HomePage getHomePage(WebDriver driver) {
        return homePage == null ? new HomePage(driver) : homePage;
    }

    public static CartPage getCartPage(WebDriver driver) {
        return cartPage == null ? new CartPage(driver) : cartPage;
    }

    public static ProductPage getProductPage(WebDriver driver) {
        return productPage == null ? new ProductPage(driver) : productPage;
    }
    public static ProductDetailPage getProductDetailPage(WebDriver driver) {
        return productDetailPage == null ? new ProductDetailPage(driver) : productDetailPage;
    }
}
