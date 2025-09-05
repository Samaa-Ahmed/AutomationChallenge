package com.vodafone.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class Validations {
    private Validations()
    {}
    @Step("Validate True")
    public static void validateTrue(boolean condition , String message)
    {
        LogsUtils.info("Validating True"+message);
        Assert.assertTrue(condition,message);
    }


    @Step("Validate Equals")
    public static void validateEquals(String actual,String expected , String message)
    {
        LogsUtils.info("Validating Equals"+message+"Actual: "+actual+"Expected: "+expected);
        Assert.assertEquals(actual,expected,message);
    }

    @Step("Validate Not Equals")
    public static void validateNotEquals(String actual,String expected , String message)
    {
        Assert.assertEquals(actual,expected,message);
    }

    @Step("Validate Page URL: {expected}")
    public static void ValidatePageUrl(WebDriver driver, String expected)
    {
        Assert.assertEquals(BrowserActions.getCurrentURL(driver).trim(),expected);
    }

    @Step("Validate Page Title: {expected}")
    public static void ValidatePageTitle(WebDriver driver,String expected)
    {
        Assert.assertEquals(BrowserActions.getPageTitle(driver),expected);
    }


}
