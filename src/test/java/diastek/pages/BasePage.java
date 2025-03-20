package diastek.pages;

import diastek.utils.SeleniumHelper;
import diastek.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static diastek.constants.FrameworkConstants.EXPLICIT_WAIT;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public static Actions action;
    SeleniumHelper seleniumHelper;
    WaitHelper waitHelper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        PageFactory.initElements(driver, this);
        seleniumHelper = new SeleniumHelper(driver);
    }
}
