package com.vodafone.pages;

import com.vodafone.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.vodafone.utils.PropertiesUtils.getPropertyValue;

public class BingResultsPage {
    private final WebDriver driver;

    // Locators
    private final By relatedSections = By.xpath("//div[h2[contains(text(),'Related searches')]]");
    private final By normalResults = By.xpath("//li[@class='b_algo']");
    private final By resultSnippets = By.xpath("//li[@class='b_algo']//p");


    private By nextPage(int page) {
        return By.xpath("//a[@aria-label='Page " + page + "']");
    }

    // Constructor
    public BingResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    @Step("Get result count")
    public int getResultCount() {
        return ElementActions.findElements(driver, normalResults).size();
    }

    @Step("Go to page {page}")
    public BingResultsPage goToPage(int page) {
        ElementActions.jsClick(driver, nextPage(page));
        LogsUtils.info("Navigated to page: " + page);
        return this;
    }

    @Step("Get related searches sections")
    public List<WebElement> getRelatedSearches() {
        return ElementActions.findElements(driver, relatedSections);
    }

    // Validations
    @Step("Validate search was successful for term: {term}")
    public BingResultsPage assertSuccessfulSearch(String term) {
        String currentUrl = BrowserActions.getCurrentURL(driver);
        Validations.validateTrue(
                currentUrl.contains("q=" + term),
                "URL does not indicate a Bing search results page for: " + term + " | Current URL: " + currentUrl
        );
        return this;
    }


    @Step("Validate result counts are equal between two pages")
    public BingResultsPage assertResultCountEqual(int countPage1, int countPage2) {
        Validations.validateEquals(
                String.valueOf(countPage1),
                String.valueOf(countPage2),
                "Result counts are not equal!"
        );
        return this;
    }


    @Step("Validate related searches section count equals {expectedCount}")
    public BingResultsPage assertRelatedSearchSectionCount(int expectedCount) {
        Validations.validateEquals(
                String.valueOf(getRelatedSearches().size()),
                String.valueOf(expectedCount),
                "Related search sections count mismatch!"
        );
        return this;
    }

    @Step("Validate related search items contain keyword: {keyword}")
    public BingResultsPage assertRelatedSearchesContainKeyword(String keyword) {
        for (WebElement section : getRelatedSearches()) {
            List<WebElement> items = section.findElements(By.tagName("a"));
            for (WebElement item : items) {
                Validations.validateTrue(
                        item.getText().toLowerCase().contains(keyword.toLowerCase()),
                        "Item [" + item.getText() + "] does not contain keyword: " + keyword
                );
            }
        }
        return this;
    }


    @Step("Assert Result Count > 0")
    public BingResultsPage assertResultCount() {
        CustomSoftAssertion.softAssertion.assertTrue(
                getResultCount() > 0,
                "No results were found!"
        );
        return this;  //
    }

    @Step("Assert Result Count on Page {page}")
    public BingResultsPage assertResultCountOnPage(int page) {
        int count = getResultCount();
        LogsUtils.info("Results count on Page " + page + " = " + count);
        Validations.validateTrue(count > 0, "No results found on Page " + page);
        return this;
    }

    @Step("Go to Page {page} and compare results count with Page {previousPage}")
    public BingResultsPage assertResultCountEqualToPage(int page, int previousPageCount) {
        ElementActions.clickElement(driver, nextPage(page));
        int currentCount = getResultCount();
        LogsUtils.info("Results count on Page " + page + " = " + currentCount);

        Validations.validateEquals(
                String.valueOf(currentCount),
                String.valueOf(previousPageCount),
                "Result count on Page " + page + " does not match Page " + (page - 1)
        );

        return this;
    }
    @Step("Validate at least two results contain keyword '{keyword}' in snippet")
    public BingResultsPage assertAtLeastTwoResultsContainKeywordInSnippet(String keyword) {
        List<WebElement> snippets = ElementActions.findElements(driver, resultSnippets);
        int matchCount = 0;

        for (WebElement snippet : snippets) {
            if (snippet.getText().toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
            }
        }

        Validations.validateTrue(matchCount >= 2,
                "Expected at least 2 results with keyword '" + keyword + "' in snippet, but found: " + matchCount);
        return this;
    }


}