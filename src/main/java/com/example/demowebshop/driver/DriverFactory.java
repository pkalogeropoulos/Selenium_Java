package com.example.demowebshop.driver;

import com.example.demowebshop.config.WebConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
// import other browsers as needed

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(WebConfig config) {
        // later: read browser from config (chrome/firefox/edge/remote)
        String browser = config.getBrowser();

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withVerbose(true)
                .withLogFile(new File("chromedriver.log"))
                .build();

        return switch (browser) {
            // you can put config-driven flags here
                // options.addArguments("--headless=new");
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                yield new ChromeDriver();
            case "chrome-headless":
                options = new ChromeOptions();
                options.addArguments("--headless=new"); // new headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                yield new ChromeDriver(options);
            default:
                WebDriverManager.chromedriver().setup();
                options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                yield new ChromeDriver(options);
        };
    }
}
