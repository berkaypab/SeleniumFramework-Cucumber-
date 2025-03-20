package diastek.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import static diastek.constants.FrameworkConstants.BROWSER_CHROME;
import static diastek.constants.FrameworkConstants.BROWSER_FIREFOX;
import static diastek.constants.FrameworkConstants.BROWSER_SAFARI;
import static diastek.constants.FrameworkConstants.BROWSER_EDGE;
import static diastek.constants.FrameworkConstants.BROWSER_OPERA;

public class DriverFactory {

    public static WebDriver initializeDriver(String browser) {
        WebDriver driver;

        switch (browser) {

            case BROWSER_CHROME: {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                //options.addArguments("--headless=new");

                driver = new ChromeDriver(options);
                break;
            }
            case BROWSER_FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case BROWSER_SAFARI: {
                driver = new SafariDriver();
                break;
            }
            case BROWSER_EDGE: {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            }
            case BROWSER_OPERA: {
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;
            }
            default:
                throw new IllegalStateException("INVALID BROWSER: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

}
