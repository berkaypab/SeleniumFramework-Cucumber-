package diastek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems = By.xpath("//div[starts-with(@class, 'product_name')]");

    @FindBy(xpath = "(//div[starts-with(@class, 'product_price')])[3]")
    private List<WebElement> itemPrices;

    @FindBy(xpath = "//div[starts-with(@class, 'product_name')]")
    private List<WebElement> productTitles;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        // Wait for cart items to load
        waitHelper.waitForAllElementToBeVisible(cartItems);

        // Check if any product title contains our product name
        for (WebElement productTitle : productTitles) {
            String title = productTitle.getText().trim();
            if (title.contains(productName)) {
                return true;
            }
        }

        return false;
    }

    public boolean isProductPriceMatching(String expectedPrice) {
        // Wait for cart items to load
        waitHelper.waitForAllElementToBeVisible(cartItems);

        // Check if any price matches our expected price
        for (WebElement priceElement : itemPrices) {
            String actualPrice = priceElement.getText().trim();
            if (actualPrice.equals(expectedPrice)) {
                return true;
            }
        }

        return false;
    }


} 