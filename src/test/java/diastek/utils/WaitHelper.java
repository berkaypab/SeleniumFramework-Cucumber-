package diastek.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class WaitHelper {

    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebDriverWait getWebDriverWait(int duration) {
        return new WebDriverWait(driver, Duration.ofSeconds(duration));
    }

    public void hardWait(int second) throws InterruptedException {
        Thread.sleep(second * 1000L);
    }

    public void hardWait(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }


    public WebElement waitForElementToBeClickable(By locator) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeClickable(By locator, int duration) {
        return getWebDriverWait(duration).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public WebElement waitForElementToBeVisible(By locator, int duration) {
        return getWebDriverWait(duration).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public List<WebElement> waitForAllElementToBeVisible(By locator) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitForAllElementToBeVisible(By locator, int duration) {
        return getWebDriverWait(duration)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitTillUrlContains(String url) {
        getWebDriverWait().until(ExpectedConditions.urlContains(url));
    }

    public void waitTillUrlContains(String url, int duration) {
        getWebDriverWait(duration).until(ExpectedConditions.urlContains(url));
    }


    public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
        getWebDriverWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void waitForFrameToBeAvailableAndSwitchToIt(By locator, int duration) {
        getWebDriverWait(duration).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }


    public void waitForAlert() {
        getWebDriverWait().until(ExpectedConditions.alertIsPresent());
    }

    public void waitForAlert(int duration) {
        getWebDriverWait(duration).until(ExpectedConditions.alertIsPresent());
    }

    // Wait for multiple conditions to be true
    public boolean waitForConditions(ExpectedCondition<?>... conditions) {
        return getWebDriverWait().until(ExpectedConditions.and(conditions));
    }

    public boolean waitForAttributeContains(By locator, String attribute, String value) {
        return getWebDriverWait()
                .until(ExpectedConditions.attributeContains(locator, attribute, value));
    }

    public boolean waitForAttributeToBe(By locator, String attribute, String value) {
        return getWebDriverWait().until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }

    public boolean waitForAttributeToBeNotEmpty(WebElement element, String attribute) {
        return getWebDriverWait().until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    // Wait for an element's DOM attribute to be a specific value
    //getAttribute-modified by JavaScript dynamically.
    //getDomAttribute-original DOM-stored attribute, ignoring JavaScript modifications.
    public boolean waitForDomAttributeToBe(WebElement element, String attribute, String value) {
        return getWebDriverWait().until(ExpectedConditions.domAttributeToBe(element, attribute, value));
    }

    // Wait for an element's DOM property to be a specific value
    public boolean waitForDomPropertyToBe(WebElement element, String property, String value) {
        return getWebDriverWait().until(ExpectedConditions.domPropertyToBe(element, property, value));
    }


    public boolean waitForElementSelectionStateToBe(By locator, boolean selected) {
        return getWebDriverWait()
                .until(ExpectedConditions.elementSelectionStateToBe(locator, selected));
    }

    // Wait for an element to be selected
    public boolean waitForElementToBeSelected(By locator) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeSelected(locator));
    }

    // Wait for an element to be invisible
    public boolean waitForInvisibilityOfElement(By locator) {
        return getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait for an element with specific text to be invisible
    public boolean waitForInvisibilityOfElementWithText(By locator, String text) {
        return getWebDriverWait()
                .until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
    }

    // Wait for JavaScript to execute without exceptions
    public boolean waitForJavaScriptThrowsNoExceptions(String javaScript) {
        return getWebDriverWait().until(ExpectedConditions.javaScriptThrowsNoExceptions(javaScript));
    }

    // Wait for JavaScript to return a value
    public Object waitForJsReturnsValue(String javaScript) {
        return getWebDriverWait().until(ExpectedConditions.jsReturnsValue(javaScript));
    }

    // Wait for a condition to be false
    public boolean waitForNot(ExpectedCondition<?> condition) {
        return getWebDriverWait().until(ExpectedConditions.not(condition));
    }

    // Wait for a specific number of elements to be present
    public List<WebElement> waitForNumberOfElementsToBe(By locator, Integer number) {
        return getWebDriverWait().until(ExpectedConditions.numberOfElementsToBe(locator, number));
    }

    // Wait for the number of elements to be less than a specific number
    public List<WebElement> waitForNumberOfElementsToBeLessThan(By locator, Integer number) {
        return getWebDriverWait()
                .until(ExpectedConditions.numberOfElementsToBeLessThan(locator, number));
    }

    // Wait for the number of elements to be more than a specific number
    public List<WebElement> waitForNumberOfElementsToBeMoreThan(By locator, Integer number) {
        return getWebDriverWait()
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
    }

    // Wait for the number of windows to be a specific number
    public boolean waitForNumberOfWindowsToBe(int expectedNumberOfWindows) {
        return getWebDriverWait()
                .until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
    }

    // Wait for multiple conditions to be true (logical OR)
    public boolean waitForOr(ExpectedCondition<?>... conditions) {
        return getWebDriverWait().until(ExpectedConditions.or(conditions));
    }

    // Wait for all elements to be present
    public List<WebElement> waitForPresenceOfAllElementsLocatedBy(By locator) {
        return getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForPresenceOfElementLocatedBy(By locator) {
        return getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for a nested element to be present
    public WebElement waitForPresenceOfNestedElementLocatedBy(By locator, By childLocator) {
        return getWebDriverWait()
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(locator, childLocator));
    }

    // Wait for a nested element to be present within a parent element
    public WebElement waitForPresenceOfNestedElementLocatedBy(WebElement element, By childLocator) {
        return getWebDriverWait()
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, childLocator));
    }

    // Wait for a condition to be refreshed
    public <T> T waitForRefreshed(ExpectedCondition<T> condition) {
        return getWebDriverWait().until(ExpectedConditions.refreshed(condition));
    }

    // Wait for an element to be stale
    public boolean waitForStalenessOf(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.stalenessOf(element));
    }

    // Wait for an element's text to match a pattern
    public boolean waitForTextMatches(By locator, Pattern pattern) {
        return getWebDriverWait().until(ExpectedConditions.textMatches(locator, pattern));
    }

    public boolean waitForTextToBe(By locator, String value) {
        return getWebDriverWait().until(ExpectedConditions.textToBe(locator, value));
    }

    public boolean waitForTextToBePresentInElement(WebElement element, String text) {
        return getWebDriverWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean waitForTextToBePresentInElementLocated(By locator, String text) {
        return getWebDriverWait()
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public boolean waitForTextToBePresentInElementValue(By locator, String text) {
        return getWebDriverWait()
                .until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    public boolean waitForTitleContains(String title) {
        return getWebDriverWait().until(ExpectedConditions.titleContains(title));
    }

    public boolean waitForTitleIs(String title) {
        return getWebDriverWait().until(ExpectedConditions.titleIs(title));
    }


    public boolean waitForUrlContains(String fraction) {
        return getWebDriverWait().until(ExpectedConditions.urlContains(fraction));
    }


    public boolean waitForUrlMatches(String regex) {
        return getWebDriverWait().until(ExpectedConditions.urlMatches(regex));
    }

    public boolean waitForUrlToBe(String url) {
        return getWebDriverWait().until(ExpectedConditions.urlToBe(url));
    }


    public List<WebElement> waitForVisibilityOfNestedElementsLocatedBy(
            WebElement element, By childLocator) {
        return getWebDriverWait()
                .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, childLocator));
    }


    public void waitForPageContentLoaded() throws InterruptedException {
        try {
            String documentReadyState;
            var retries = 5;
            do {
                documentReadyState =
                        ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
                hardWait(1);
                retries--;
            } while (!documentReadyState.equals("complete") && retries > 0);
        } catch (Exception _) {
        }

        getWebDriverWait()
                .until(
                        driver ->
                                ((JavascriptExecutor) driver)
                                        .executeScript("return document.readyState")
                                        .equals("complete"));
    }

    public void waitForAjax() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wdriver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return jQuery.active == 0").equals(true);
            }
        });
    }
}
