package com.vodafone.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waits
{
    //wait for element to be present
    public static WebElement waitForElementPresent(WebDriver driver, By locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 ->
                        driver1.findElement(locator));

    }

    //wait for element to be visible

    public static WebElement waitForElementVisible(WebDriver driver, By locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 ->
                        {
                            WebElement element =waitForElementPresent(driver,locator);
                            return element.isDisplayed() ? element: null;
                        }
                );
    }

    public static List<WebElement> waitForPresenceOfAllElements(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.findElements(locator));
    }


    //wait for element to be clickable
    public static WebElement waitForElementClickable(WebDriver driver, By locator)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver1 ->
                        {
                            WebElement element =waitForElementVisible(driver,locator);
                            return element.isEnabled() ? element: null;
                        }
                );
    }




}
