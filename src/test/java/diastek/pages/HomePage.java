package diastek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

    private final By acceptCookiesBtn = By.cssSelector("button[id*='onetrust-accept-btn-handler']");
    private final By acceptCookiesPolicyBtn = By.id("onetrust-accept-btn-handler");
    private final By allCategoriesMenu = By.xpath("//span[text()='Elektronik']");
    private final By tabletSubCategoryMenu = By.xpath("//a[text()='Bilgisayar/Tablet']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() throws InterruptedException {
        seleniumHelper.loadBaseURL();
        waitHelper.waitForPageContentLoaded();
        return this;
    }

    public HomePage acceptCookiesIfPresent() {
        try {
            if (seleniumHelper.isElementDisplayed(acceptCookiesBtn)) {
                seleniumHelper.clickOn(acceptCookiesBtn);
            }
        } catch (Exception e) {
            // Cookie banner might not be present
            e.printStackTrace();
        }

        return this;
    }

    public HomePage hoverOnAllCategories() throws InterruptedException {
        action = new Actions(driver);
        seleniumHelper.waitForPresenceOfElementLocatedBy(allCategoriesMenu);
        seleniumHelper.hoverElement(allCategoriesMenu);
        seleniumHelper.hoverElement(tabletSubCategoryMenu);

        seleniumHelper.clickOn(tabletSubCategoryMenu);
        return this;
    }


    public HomePage navigateThroughCategories() throws InterruptedException {
        hoverOnAllCategories();
        return this;
    }
} 