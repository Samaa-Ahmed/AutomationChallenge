package com.vodafone.tests;

import com.vodafone.Listners.TestNGListeners;
import com.vodafone.drivers.DriverManager;
import com.vodafone.pages.BingHomePage;
import com.vodafone.pages.BingResultsPage;
import com.vodafone.utils.BrowserActions;
import com.vodafone.utils.JsonUtils;
import com.vodafone.utils.PropertiesUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class TC002_RelatedSearchesSection
{
    JsonUtils testData;

    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void validatePage1results()
    {
        new BingResultsPage(DriverManager.getDriver())
                .assertRelatedSearchSectionCount(2)
                .assertAtLeastTwoResultsContainKeywordInSnippet(testData.getJsonData("search.term"));
    }
    @BeforeTest
    public void beforeClass()
    {
        testData =new JsonUtils("test-data");

    }

    @AfterTest
    public void tearDown() {
        BrowserActions.closeBrowser(DriverManager.getDriver());
    }
}

