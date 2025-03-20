package diastek.stepdefinitions;

import org.openqa.selenium.WebDriver;

import diastek.context.TestContext;

public class MyStepDefinitions {
    private final WebDriver driver;


    public MyStepDefinitions(TestContext context){
        driver = context.driver;
    }
}
