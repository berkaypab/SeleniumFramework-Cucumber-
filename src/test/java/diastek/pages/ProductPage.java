package diastek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductPage extends BasePage {


    private final By brandFilterSelector = By.xpath("//a[@title='Apple Bilgisayar']/preceding::input[1]");
    private final By screenSizeFilterSelector = By.xpath("//div[text()='13,3 inç']/preceding::input[1]");
    private final By brandFilterContainer = By.cssSelector("#markalar");
    private final By screenSizeFilterContainer = By.cssSelector("#ekranboyutu");
    private final By mainFilterContainer = By.cssSelector("#stickyVerticalFilter");
    private final By productCards = By.xpath("//div[starts-with(@class, 'productListContent')]//li");
    private final By finalPrices = By.xpath("//*[starts-with(@class, 'price') and starts-with(@data-test-id, 'final-price')]");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage filterByBrand() throws InterruptedException {

        seleniumHelper.waitTillPageLoadedProperly();
        seleniumHelper.scrollWithContainer(mainFilterContainer, brandFilterContainer);
        seleniumHelper.clickOn(brandFilterSelector);
        seleniumHelper.refreshPage();
        return this;
    }

    public ProductPage filterByScreenSize() throws InterruptedException {
        seleniumHelper.waitTillPageLoadedProperly();
        WebElement container = seleniumHelper.waitForPresenceOfElementLocatedBy(mainFilterContainer);
        WebElement screenFilter = seleniumHelper.waitForPresenceOfElementLocatedBy(screenSizeFilterSelector);
        seleniumHelper.scrollWithContainer(mainFilterContainer, screenSizeFilterContainer);

        seleniumHelper.clickOn(screenSizeFilterSelector);
        seleniumHelper.refreshPage();
        return this;
    }

    public ProductDetailPage selectMostExpensiveProduct() throws InterruptedException {

        List<WebElement> products = seleniumHelper.waitForPresenceOfAllElementsLocatedBy(productCards);
        List<WebElement> priceElements = seleniumHelper.waitForPresenceOfAllElementsLocatedBy(finalPrices);


        WebElement mostExpensiveProduct = null;
        double highestPrice = 0.0;

        for (int i = 0; i < products.size(); i++) {
            try {
                String priceText = priceElements.get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".");

                double price = Double.parseDouble(priceText);

                if (price > highestPrice) {
                    highestPrice = price;
                    mostExpensiveProduct = products.get(i);
                }

            } catch (NoSuchElementException | NumberFormatException e) {


            }
        }


        if (mostExpensiveProduct != null) {
            //scrollToElement(mostExpensiveProduct);
            seleniumHelper.clickOn(mostExpensiveProduct);
            switchToProductDetailPage();
        } else {
            throw new RuntimeException("No products found with valid prices");
        }

        return new ProductDetailPage(driver);
    }

    public void switchToProductDetailPage() {
        seleniumHelper.switchToWindowByIndex(1);
    }
} 