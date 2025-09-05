package com.vodafone.pages;

import com.vodafone.utils.BrowserActions;
import com.vodafone.utils.ElementActions;
import com.vodafone.utils.Validations;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.vodafone.utils.PropertiesUtils.getPropertyValue;

public class BingHomePage {

    //locators
    private final WebDriver driver;
    private final By searchBox = By.name("q");
    private final By searchButton = By.id("search_icon");
    private final By suggestionsList = By.cssSelector("li.sa_sg");



    //Constructor
    public BingHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public BingHomePage navigateToBingHomePage() {
        BrowserActions.navigateToURL(driver, getPropertyValue("baseURL"));

        return this;
    }


    //Actions
    @Step("Enter serach term: {term}")
    public BingHomePage enterSearchTerm(String term) {
        ElementActions.sendData(driver, searchBox, term);
        return this;
    }

    @Step("Click on Search Button")
    public BingResultsPage clickSearch() {
        ElementActions.clickElement(driver, searchButton);
        return new BingResultsPage(driver);
    }

    @Step("Submit Search using Enter Key")
    public BingResultsPage submitSearch() {
        ElementActions.findElement(driver, searchBox).submit();
        return new BingResultsPage(driver);
    }


    //Validations
    @Step("Validate Home Page Title contains 'Bing'")
    public BingHomePage assertHomePageTitle() {
        Validations.ValidatePageTitle(driver, getPropertyValue("homeTitle"));
        return this;
    }

    @Step("Validate Home Page URL")
    public BingHomePage assertHomePageUrl() {
        Validations.ValidatePageUrl(driver, getPropertyValue("baseURL"));
        return this;
    }


}






