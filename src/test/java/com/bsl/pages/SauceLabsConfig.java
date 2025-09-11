package com.bsl.pages;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import static com.codeborne.selenide.Configuration.browser;

public class SauceLabsConfig {

    public static void setup() throws Exception {

        //Sauce Labs Config
        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        String sauceUrl = System.getenv("SAUCE_URL");


        MutableCapabilities options;

        switch (browser.toLowerCase()) {
            case "firefox":
                options = new FirefoxOptions();
                break;
            case "edge":
                options = new EdgeOptions();
                break;
            default:
                options = new ChromeOptions();
        }

        options.setCapability("platformName", "Windows 11");
        options.setCapability("browserVersion", "latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", username);
        sauceOptions.put("accessKey", accessKey);
        sauceOptions.put("name", "Cross Browser Test - " + browser);

        options.setCapability("sauce:options", sauceOptions);

        // Configuración Selenide
        Configuration.remote = sauceUrl;
        browser = browser;
        Configuration.browserCapabilities = options;
        Configuration.browserSize = "1920x1080";
    }
}
