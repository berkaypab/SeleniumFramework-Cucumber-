package diastek.context;

import org.openqa.selenium.WebDriver;

public class TestContext {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public void removeDriver() {
        driverThreadLocal.remove();
    }
}
