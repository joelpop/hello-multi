package it.ui;

import com.vaadin.testbench.BrowserTestBase;
import com.vaadin.testbench.DriverSupplier;
import com.vaadin.testbench.TestBench;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Base class for all TestBench integration tests, allowing you to change the applicable driver,
 * test URL or other configurations in one place.
 */
@SuppressWarnings("java:S5786")  // JUnit5 test classes and methods should have default package visibility
public class BrowserDriverTestBase extends BrowserTestBase implements DriverSupplier {

    private static final String CONTEXT_ROOT = "http://%s:%s"
            .formatted(System.getProperty("server.address", "localhost"),
                    System.getProperty("server.port", "8080"));

    @Override
    public WebDriver createDriver() {
        var chromeOptions = new ChromeOptions();
        if (Boolean.getBoolean("com.vaadin.testbench.Parameters.headless")) {
            chromeOptions.addArguments("--headless=new");
        }

        return TestBench.createDriver(new ChromeDriver(chromeOptions));
    }

    @BeforeEach
    void setUp() {
        getDriver().get(CONTEXT_ROOT);
    }

    @AfterEach
    void afterEach() {
        getDriver().close();
    }
}
