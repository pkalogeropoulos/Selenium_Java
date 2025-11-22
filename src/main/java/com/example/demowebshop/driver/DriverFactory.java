package com.example.demowebshop.driver;

import com.example.demowebshop.config.WebConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// import other browsers as needed

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(WebConfig config) {
        // later: read browser from config (chrome/firefox/edge/remote)
        String browser = config.getBrowser();


        return switch (browser) {
            // you can put config-driven flags here
                // options.addArguments("--headless=new");
            case "chrome":
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            case "chrome-headless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); // new headless mode
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                yield new ChromeDriver(options);
            default:
                WebDriverManager.chromedriver().setup();
                options = new ChromeOptions();
                yield new ChromeDriver(options);
        };
    }
}
