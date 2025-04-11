package diastek.hooks;

import diastek.context.TestContext;
import diastek.factory.DriverFactory;
import diastek.utils.ZipUtils;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static diastek.constants.FrameworkConstants.*;
import static java.lang.System.out;

public class MyHooks {
    private final TestContext context;

    public MyHooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void before(Scenario scenario) {
        out.println("BEFORE: THREAD ID : " + Thread.currentThread().getId() + "," + "SCENARIO NAME: " + scenario.getName());

        WebDriver driver = DriverFactory.initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_CHROME));
        context.setDriver(driver);
    }

    @After
    public void after(Scenario scenario) {
        out.println("AFTER: THREAD ID : " + Thread.currentThread().getId() + "," + "SCENARIO NAME: " + scenario.getName());

        WebDriver driver = context.getDriver();
        
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        driver.quit();
        context.removeDriver();
    }

    @BeforeAll
    public static void beforeAll() {
        // ExtentReport.initReports();
    }

    @AfterAll
    public static void afterAll() {
        // ExtentReport.flushReports();
        ZipUtils.zip();
        // EmailSendUtils.sendEmail();
    }
}
