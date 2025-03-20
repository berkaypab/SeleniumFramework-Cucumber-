package diastek.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SeleniumHelper {

    public static Actions action;

    private final WaitHelper waitHelper;
    private final JavaScriptHelper jsHelper;
    private final WebDriver driver;
    private static final boolean IS_DEBUG = true;
    //private static final boolean IS_DEBUG = Boolean.parseBoolean(Dotenv.configure().ignoreIfMissing().load().get("IS_DEBUG"));

    public SeleniumHelper(WebDriver driver) {
        action = new Actions(driver);
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        jsHelper = new JavaScriptHelper(driver);
    }


    private WebElement waitTillElementIsEnable(WebElement element) throws InterruptedException {
        var numberOfTries = 1;
        while (!isElementEnable(element) && numberOfTries != 15) {
            waitHelper.hardWait(numberOfTries);
            numberOfTries++;
        }
        if (!isElementEnable(element)) throw new RuntimeException("Element is not in enable state");
        return element;
    }

    public void loadBaseURL() {
        driver.get(ConfigLoader.getInstance().getBaseUrl());
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    private void clickOnElement(WebElement element) {
        var tryToClickOnElement = 10;
        boolean staleElement;

        do {
            try {
                element.click();
                staleElement = false;
            } catch (Exception e) {
                staleElement = true;
            }
            tryToClickOnElement--;
        } while (tryToClickOnElement != 0 && staleElement);
    }


    private void enterText(WebElement element, String value) throws InterruptedException {
        var enableElement = waitTillElementIsEnable(element);
        enableElement.clear();
        enableElement.sendKeys(value);
    }


    private boolean isElementEnable(WebElement element) {
        return element.isEnabled();
    }


    public boolean isElementEnabled(By by) {
        var elementIsEnable = waitHelper.waitForElementToBeVisible(by);
        return isElementEnable(elementIsEnable);
    }

    public boolean isElementEnabled(By by, int second) {
        var elementIsEnable = waitHelper.waitForElementToBeVisible(by, second);
        return isElementEnable(elementIsEnable);
    }


    public void scrollAndEnterText(By by, String value) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        enterText(element, value);
    }

    public void scrollAndEnterText(By by, String value, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        enterText(element, value);
    }

    public void scrollWithContainer(By containerLocator, By targetLocator) throws InterruptedException {
        WebElement target = waitHelper.waitForPresenceOfElementLocatedBy(targetLocator);
        WebElement container = waitHelper.waitForPresenceOfElementLocatedBy(containerLocator);
        jsHelper.scrollToElementWithinContainer(container, target);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(target);
    }


    public void enterText(By by, String value) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        enterText(element, value);
    }

    public void enterText(By by, String value, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        enterText(element, value);
    }


    public void scrollAndClickOn(By by) throws InterruptedException {
        var element = waitHelper.waitForElementToBeClickable(by);
        var elementIsEnable = waitTillElementIsEnable(element);
        jsHelper.scrollToElementIfNotInView(elementIsEnable);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        clickOnElement(elementIsEnable);
    }

    public void scrollAndClickOn(By by, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeClickable(by, second);
        var elementIsEnable = waitTillElementIsEnable(element);
        jsHelper.scrollToElementIfNotInView(elementIsEnable);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        clickOnElement(elementIsEnable);
    }

    public void clickOn(By by) throws InterruptedException {
        var element = waitHelper.waitForElementToBeClickable(by);
        var elementIsEnable = waitTillElementIsEnable(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        clickOnElement(elementIsEnable);
    }

    public void clickOn(WebElement element) throws InterruptedException {
        var elementIsEnable = waitTillElementIsEnable(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        clickOnElement(elementIsEnable);
    }

    public void clickOn(By by, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeClickable(by, second);
        var elementIsEnable = waitTillElementIsEnable(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        clickOnElement(elementIsEnable);
    }


    public boolean isElementDisplayed(By by) {
        WebElement element = null;
        try {
            element = waitHelper.waitForElementToBeVisible(by);
            if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        } catch (Exception _) {
        }
        return element != null;
    }

    public boolean isElementDisplayed(By by, int second) {
        WebElement element = null;
        try {
            element = waitHelper.waitForElementToBeVisible(by, second);
            if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        } catch (Exception _) {
        }
        return element != null;
    }


    public void selectOptionByText(By by, String text) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        var elementIsEnable = waitTillElementIsEnable(element);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        new Select(element).selectByVisibleText(text);
    }

    public void selectOptionByText(By by, String text, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        new Select(element).selectByVisibleText(text);
    }


    public void selectOptionByValue(By by, String value) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        new Select(element).selectByValue(value);
    }

    public void selectOptionByValue(By by, String value, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        new Select(element).selectByValue(value);
    }


    public void selectOptionByIndex(By by, int index) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        new Select(element).selectByIndex(index);
    }

    public void selectOptionByIndex(By by, int index, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        new Select(element).selectByIndex(index);
    }


    public List<String> getAllOptionText(By by) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        return new Select(element).getOptions().stream().map(WebElement::getText).toList();
    }

    public List<String> getAllOptionText(By by, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        return new Select(element).getOptions().stream().map(WebElement::getText).toList();
    }


    public void switchToIframe(By by) {
        waitHelper.waitForFrameToBeAvailableAndSwitchToIt(by);
    }

    public void switchToIframe(By by, int second) {
        waitHelper.waitForFrameToBeAvailableAndSwitchToIt(by, second);
    }


    public boolean isRadioButtonSelected(By by) {
        return waitHelper.waitForElementToBeVisible(by).isSelected();
    }

    public boolean isRadioButtonSelected(By by, int second) {
        return waitHelper.waitForElementToBeVisible(by, second).isSelected();
    }

    public boolean isCheckBoxButtonSelected(By by) {
        return waitHelper.waitForElementToBeVisible(by).isSelected();
    }

    public boolean isCheckBoxButtonSelected(By by, int second) {
        return waitHelper.waitForElementToBeVisible(by, second).isSelected();
    }


    public void clickOnElementUsingJavaScript(By by) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        jsHelper.javaScriptClickOn(element);
    }

    public void clickOnElementUsingJavaScript(By by, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        jsHelper.javaScriptClickOn(element);
    }


    public void enterTextUsingJavaScript(By by, String value) {
        var element = waitHelper.waitForElementToBeVisible(by);
        jsHelper.javaScriptEnterText(element, value);
    }

    public void enterTextUsingJavaScript(By by, String value, int second) {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        jsHelper.javaScriptEnterText(element, value);
    }


    public void scrollToTheElement(By by) {
        var element = waitHelper.waitForElementToBeVisible(by);
        jsHelper.scrollToElementCenter(element);
    }

    public void scrollToTheElement(By by, int second) {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        jsHelper.scrollToElementCenter(element);
    }


    public String getText(By by) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        return element.getText().trim();
    }

    public String getText(By by, int second) throws InterruptedException {
        var element = waitHelper.waitForElementToBeVisible(by, second);
        jsHelper.scrollToElementIfNotInView(element);
        if (IS_DEBUG) jsHelper.javaScriptHighlightElement(element);
        return element.getText().trim();
    }


    public List<String> getAllElementsText(By by) {
        var elements = waitHelper.waitForAllElementToBeVisible(by);
        if (IS_DEBUG)
            try {
                for (WebElement element : elements) jsHelper.javaScriptHighlightElement(element);
            } catch (Exception _) {
            }
        return elements.stream().map(x -> x.getText().trim().replace("\n", "")).toList();
    }

    public List<String> getAllElementsText(By by, int second) {
        var elements = waitHelper.waitForAllElementToBeVisible(by, second);
        if (IS_DEBUG)
            try {
                for (WebElement element : elements) jsHelper.javaScriptHighlightElement(element);
            } catch (Exception _) {
            }
        return elements.stream().map(x -> x.getText().trim().replace("\n", "")).toList();
    }

    public boolean checkPresenceOfAllElements(By by) {
        try {
            List<WebElement> elements = waitHelper.waitForPresenceOfAllElementsLocatedBy(by);
            if (elements.isEmpty()) {
                return false;
            }
            for (WebElement element : elements) {
                jsHelper.scrollToElementIfNotInView(element);
                //waitHelper.hardWait(1);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitTillPageLoadedProperly() throws InterruptedException {
        waitHelper.waitForPageContentLoaded();

    }

    public void waitForPresenceOfNestedElementLocatedBy(By locator, By childLocator) {
        WebElement element = waitHelper.waitForPresenceOfNestedElementLocatedBy(locator, childLocator);
        isElementEnable(element);
    }


    public String getPageTitle() throws InterruptedException {
        waitTillPageLoadedProperly();
        return driver.getTitle();
    }


    public boolean isElementHaveGivenClass(By by, String cssValue) {
        var element = waitHelper.waitForElementToBeVisible(by);
        return element.getAttribute("class").contains(cssValue);
    }

    public void navigateTo(String url) throws InterruptedException {
        driver.navigate().to(url);
        waitTillPageLoadedProperly();
    }

    public void waitForNumberOfWindowsToBe(int expectedNumberOfWindows) {
        waitHelper.waitForNumberOfWindowsToBe(2);
    }

    public void waitForAjaxLoad() {
        waitHelper.waitForAjax();
    }

    public void switchToTab(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    public void closeCurrentTab() {
        driver.close();
    }

    public void switchBackToOriginalTab(String originalWindowHandle) {
        driver.switchTo().window(originalWindowHandle);
    }

    public void switchToWindowByIndex(int index) {
        Set<String> allWindows = driver.getWindowHandles();
        List<String> windowList = new ArrayList<>(allWindows);

        if (index >= 0 && index < windowList.size()) {
            driver.switchTo().window(windowList.get(index));
        } else {
            throw new NoSuchWindowException("The specified window could not be found: " + index);
        }
    }

    public void hoverElement(By by) {
        waitHelper.waitForElementToBeVisible(by);
        var elementIsEnable = driver.findElement(by);
        action.moveToElement(elementIsEnable).build().perform();
    }

    public void waitForInvisibilityOfElement(By by) {
        waitHelper.waitForInvisibilityOfElement(by);
    }

    public void waitForInvisibilityOfElementWithText(By by, String text) {
        waitHelper.waitForInvisibilityOfElementWithText(by, text);
    }

    public void waitForRefreshedElement(By by) {
        waitHelper.waitForRefreshed(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForPresenceOfElementLocatedBy(By by) {
        return waitHelper.waitForPresenceOfElementLocatedBy(by);
    }

    public List<WebElement> waitForPresenceOfAllElementsLocatedBy(By by) {
        return waitHelper.waitForPresenceOfAllElementsLocatedBy(by);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}