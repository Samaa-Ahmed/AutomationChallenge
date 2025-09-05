package com.vodafone.drivers;

import com.vodafone.utils.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }


    @Step("Create instance of browser : {browserName}")
    public static WebDriver createInstance(String browserName) {
        WebDriver driver = BrowserFactory.getBrowser(browserName);
        LogsUtils.info("Driver is created:", browserName);
        setDriver(driver);
        return getDriver();
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            LogsUtils.error("Driver is null");
            fail("Driver is null");
        }
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

}
