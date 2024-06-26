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
 * Base class for all TestBench tests, allowing you to change the applicable driver,
 * test URL or other configurations in one place.
 */
public class BrowserDriverTestBase extends BrowserTestBase implements DriverSupplier {
    public final String CONTEXT_ROOT = "http://%s:%s"
            .formatted(System.getProperty("server.address", "localhost"),
                    System.getProperty("server.port", "8080"));

    @Override
    public WebDriver createDriver() {
        var options = new ChromeOptions();
        if (Boolean.getBoolean("com.vaadin.testbench.Parameters.headless")) {
            options.addArguments("--headless=new");
        }
        return TestBench.createDriver(new ChromeDriver(options));
    }

    @BeforeEach
    public void setUp() {
        getDriver().get(CONTEXT_ROOT);
    }

    @AfterEach
    public void afterEach() {
        getDriver().close();
    }
}
