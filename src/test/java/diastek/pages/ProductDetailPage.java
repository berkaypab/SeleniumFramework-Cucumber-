package diastek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BasePage {

    private final By productName = By.xpath("//*[@data-test-id='title']");
    private final By productPrice = By.xpath("//div[@data-test-id='default-price']//span");
    private final By addToCartButton = By.xpath("//button[@data-test-id='addToCart'] ");
    private final By checkoutModal_GoToCart = By.xpath("//button[text()='Sepete git']");


    private String productPriceValue;
    private String productNameValue;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice() throws InterruptedException {
        seleniumHelper.waitForPresenceOfElementLocatedBy(productPrice);
        productPriceValue = seleniumHelper.getText(productPrice).trim();

        return productPriceValue;
    }

    public String getProductName() throws InterruptedException {
        seleniumHelper.waitForPresenceOfElementLocatedBy(productName);
        productNameValue = seleniumHelper.getText(productName).trim();

        return productNameValue;
    }

    public ProductDetailPage addToCart() throws InterruptedException {
        seleniumHelper.clickOn(addToCartButton);
        // Wait for confirmation toast
        seleniumHelper.waitForPresenceOfElementLocatedBy(checkoutModal_GoToCart);
        return this;
    }

    public CartPage goToCart() throws InterruptedException {
        seleniumHelper.clickOn(checkoutModal_GoToCart);
        return new CartPage(driver);
    }

    public String getSavedProductPrice() {
        return productPriceValue;
    }

    public String getSavedProductName() {
        return productNameValue;
    }
} 