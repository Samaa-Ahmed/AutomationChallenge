package com.vodafone.drivers;

import com.vodafone.utils.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

public class BrowserFactory
{
    public static WebDriver getBrowser(String browserType)
    {
        switch (browserType.toLowerCase()) {
            case "chrome":
                ChromeOptions options = getChromeOptions();
                return new ChromeDriver(options);
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                return new FirefoxDriver(firefoxOptions);
            default:
                EdgeOptions edgeOptions = getEdgeOptions();
                return new EdgeDriver(edgeOptions);
        }
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-notifactions");
        edgeOptions.addArguments("--remote-allow-origins");
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        edgeOptions.setExperimentalOption("useAutomationExtension", false);
        edgeOptions.addArguments(
                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36"
        );
        if (!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local"))
        {
            edgeOptions.addArguments("--headless");
        }
        Map<String, Object> edgePrefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false);
        edgeOptions.setExperimentalOption("prefs",edgePrefs);
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return edgeOptions;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments(
                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36"
        );
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");


        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        options.addArguments(
                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36"
        );
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local"))
        {
            options.addArguments("--headless");
        }

        Map<String, Object> prefs = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false);
        options.setExperimentalOption("prefs",prefs);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;


    }
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifactions");
        options.addArguments("--remote-allow-origins");
        options.addArguments(
                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36"
        );
        if(!PropertiesUtils.getPropertyValue("executionType").equalsIgnoreCase("local"))
        {
            options.addArguments("--headless");
        }
       options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);
        return options;
    }
}
