package com.example.demowebshop.tests.base;

import com.example.demowebshop.config.AppConfig;
import com.example.demowebshop.driver.DriverFactory;
import com.example.demowebshop.driver.DriverManager;
import com.example.demowebshop.pages.Pages;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base class for all UI tests.
 * <p>
 * Responsibilities:
 * - Load AppConfig (singleton) once per suite
 * - Create a fresh WebDriver per test method
 * - Register driver in DriverManager (ThreadLocal) for POMs to use
 * - Apply common browser settings (window, timeouts)
 * - Tear down driver and optionally capture screenshots on failure
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected AppConfig config;
    protected Pages pages;

    @BeforeSuite(alwaysRun = true)
    public void initConfig() {
        // Force early config load so any config problems fail fast
        this.config = AppConfig.getInstance();
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        var webCfg = config.web(); // e.g. WebConfig

        // Create a new driver for this test method
        driver = DriverFactory.createDriver(webCfg);

        // Register driver in ThreadLocal, so POMs can use DriverManager.getDriver()
        DriverManager.setDriver(driver);

        // Common browser setup
        driver.manage().window().maximize();

        // Timeouts driven by config (strongly typed)
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(webCfg.getExplicitWait())
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // we prefer explicit waits

        // Create Pages facade bound to this driver
        pages = new Pages(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        // Always quit the driver and clear ThreadLocal
        DriverManager.quitDriver();
    }

    /**
     * Capture a screenshot when a test fails.
     * Screenshots are stored under: target/screenshots/{testName}-{timestamp}.png
     * Note that this screenshot could be stored also in Allure for better reporting
     */
    @AfterMethod(alwaysRun = true)
    protected void captureScreenshot(ITestResult result) {
        if (result.isSuccess()) {
            return; //all went good, no need for a screenshot
        }

        if (!(driver instanceof TakesScreenshot)) {
            return;
        }

        String testName = result.getMethod().getMethodName();
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        Path screenshotsDir = Path.of("target", "screenshots");
        try {
            Files.createDirectories(screenshotsDir);
        } catch (IOException ignored) {
            // Best-effort; if this fails we won't break the test teardown
        }

        Path screenshotFile = screenshotsDir.resolve(testName + "-" + timestamp + ".png");

        try {
            byte[] data = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(screenshotFile, data);
        } catch (Exception e) {
            // Log and move on – don't let screenshot failure kill the teardown
            System.err.println("Failed to capture screenshot for test " + testName + ": " + e.getMessage());
        }
    }
}
