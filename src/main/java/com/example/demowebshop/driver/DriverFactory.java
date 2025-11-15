package com.example.demowebshop.driver;

import com.example.demowebshop.config.WebConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// import other browsers as needed

public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(WebConfig config) {
        // later: read browser from config (chrome/firefox/edge/remote)
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();
                // you can put config-driven flags here
                // options.addArguments("--headless=new");
                return new ChromeDriver(options);
        }
    }
}
