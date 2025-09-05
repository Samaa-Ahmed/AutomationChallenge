package com.vodafone.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.vodafone.utils.ElementActions.findElement;

public class Scroll
{
    private Scroll() {}
    public static void scrollToElement(WebDriver driver, By locator)
    {
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView(true);",ElementActions.findElement(driver,locator));
    }


}
