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

    private WebDriver driver;
    private final TestContext context;

    /**
     * Dependency Injection using Pico container
     */
    public MyHooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void before(Scenario scenario) {

        out.println("BEFORE: THREAD ID : " + Thread.currentThread().getId() + "," + "SCENARIO NAME: " + scenario.getName());

        driver = DriverFactory.initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_CHROME));
//        driver = DriverFactory.initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_FIREFOX));
//        driver = DriverFactory.initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_EDGE));
//        driver = DriverFactory.initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_OPERA));
//        driver = DriverFactory.initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_SAFARI));

        context.driver = driver;

    }

    @After
    public void after(Scenario scenario) {
        out.println("AFTER: THREAD ID : " + Thread.currentThread().getId() + "," + "SCENARIO NAME: " + scenario.getName());

        /* If Scenario fails */
        /* This is for attaching the screenshot in Cucumber report */
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        // doExtentReportWork(scenario);

        driver.quit();
    }

//	private void doExtentReportWork(Scenario scenario) {
//
//		if (scenario.getStatus().toString().equals("PASSED")) {
//			ExtentLogger.pass("<b><i>" + scenario.getName() + "</i></b>");
//			ExtentLogger.pass("<b><i>" + scenario.getStatus().PASSED + "</i></b>");
//		}
//
//		if (scenario.getStatus().toString().equals("SKIPPED")) {
//			ExtentLogger.skip("<b><i>" + scenario.getName() + "</i></b>");
//			ExtentLogger.skip("<b><i>" + scenario.getStatus().SKIPPED + "</i></b>");
//		}
//
//		if (scenario.isFailed()) {
//			ExtentLogger.fail("<b><i>" + scenario.getName() + "</i></b>");
//			ExtentLogger.fail("<b><i>" + scenario.getStatus().FAILED + "</i></b>");
//		}
//
//	}

//	@BeforeStep
//	public static void beforeStep() {
//		ExtentLogger.pass("<b><i>" + "BEFORE STEP" + "</i></b>");
//	}

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
