package com.vodafone.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserActions {
    private BrowserActions() {
    }

    @Step("Navigate to URL: {url}")
    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
        LogsUtils.info("Navigated to: " + url);
    }


    public static void jsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        LogsUtils.info("Clicked element using JS: " + element);
    }


    @Step("Get current URL")
    public static String getCurrentURL(WebDriver driver) {
        LogsUtils.info("Current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Get page title")
    public static String getPageTitle(WebDriver driver) {
        LogsUtils.info("Page title: " + driver.getTitle());
        return driver.getTitle();

    }
    @Step("Close the browser")
    public static void closeBrowser(WebDriver driver) {
        LogsUtils.info("Closing the browser");
        driver.quit();
    }
}
