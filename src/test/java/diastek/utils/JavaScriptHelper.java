package diastek.utils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

    private final JavascriptExecutor js;
    private final WaitHelper waitHelper;

    public JavaScriptHelper(WebDriver driver) {
        js = (JavascriptExecutor) driver;
        waitHelper = new WaitHelper(driver);
    }

    public void scrollToElementWithinContainer(WebElement container, WebElement target) {

        if (container != null && target != null) {
            String jsScript =
                    "var container = arguments[0];" +
                            "var element = arguments[1];" +
                            "var elementTop = element.offsetTop;" +
                            "container.scrollTo({top: elementTop - (container.clientHeight / 2), behavior: 'smooth'});";

            js.executeScript(jsScript, container, target);
        } else {
            System.out.println("Container veya hedef element bulunamadı.");
        }
    }

    private boolean isElementInViewport(WebElement element) {
        var jsQuery =
                """
                          var rect = arguments[0].getBoundingClientRect();
                          return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && rect.right <= (window.innerWidth || document.documentElement.clientWidth));
                        """;
        return (Boolean) js.executeScript(jsQuery, element);
    }

    // used this method when element is not in view port
    void scrollToElementCenter(WebElement element) {
        var jsQuery =
                """
                        var elementRect = arguments[0].getBoundingClientRect();
                        var absoluteElementTop = elementRect.top + window.pageYOffset;
                        var middle = absoluteElementTop - (window.innerHeight / 2);
                        window.scrollTo(0, middle);
                        """;
        js.executeScript(jsQuery, element);
    }

    // use this method to check and ensure element is in view port
    void scrollToElementIfNotInView(WebElement element) {
        if (!isElementInViewport(element)) {
            scrollToElementCenter(element);
        }
    }
    void scrollToElementIfNotInView(WebElement container, WebElement target) {
        if (!isElementInViewport(target)) {
            scrollToElementWithinContainer(container, target);
        }
    }

    void javaScriptEnterText(WebElement element, String value) {
        js.executeScript("arguments[0].value='arguments[1];", element, value);
    }

    void javaScriptClickOn(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    void javaScriptHighlightElement(WebElement element) throws InterruptedException {
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        waitHelper.hardWait(2);
    }
}
