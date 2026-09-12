package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

public class Hooks {

    @Before
    public void setUp() {
        DriverManager.getDriver().driver.manage().window().maximize();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.getDriver().driver != null) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver().driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        try { DriverManager.getDriver().driver.quit(); } catch (Exception ignored) {}
        try {
            java.lang.reflect.Field field = DriverManager.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (Exception ignored) {}
    }
}
