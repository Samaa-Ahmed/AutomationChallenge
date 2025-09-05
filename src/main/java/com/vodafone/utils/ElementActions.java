package com.vodafone.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementActions {
    private ElementActions() {
    }

    public static void sendData(WebDriver driver, By locator, String data) {
        Waits.waitForElementVisible(driver, locator);
        Scroll.scrollToElement(driver, locator);
        findElement(driver, locator).sendKeys(data);
        LogsUtils.info("Data Entered: " + data + " in the to field : " + locator.toString());
    }


    public static void clickElement(WebDriver driver, By locator) {
        Waits.waitForElementClickable(driver, locator);
        Scroll.scrollToElement(driver, locator);
        findElement(driver, locator).click();
        LogsUtils.info("Clicked on the element : " + locator.toString());

    }


    public static WebElement findElement(WebDriver driver, By locator) {
        LogsUtils.info("Finding the element : " + locator.toString());
        Waits.waitForElementVisible(driver, locator);
        return driver.findElement(locator);
    }

    public static List<WebElement> findElements(WebDriver driver, By locator) {
        LogsUtils.info("Finding elements by locator: " + locator.toString());
        Waits.waitForPresenceOfAllElements(driver, locator);
        return driver.findElements(locator);
    }

    public static void jsClick(WebDriver driver, By locator) {
        int attempts = 0;
        while (attempts < 3) { // retry up to 3 times
            try {
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(locator));
                Scroll.scrollToElement(driver, locator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                LogsUtils.info("Clicked element using JS: " + locator);
                return; // exit after successful click
            } catch (StaleElementReferenceException e) {
                LogsUtils.warn("Stale element, retrying click: " + locator);
                attempts++;
            }
        }
    }

}
