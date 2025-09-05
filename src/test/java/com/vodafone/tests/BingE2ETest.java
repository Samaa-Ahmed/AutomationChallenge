package com.vodafone.tests;

import com.vodafone.Listners.TestNGListeners;
import com.vodafone.drivers.DriverManager;
import com.vodafone.pages.BingHomePage;
import com.vodafone.pages.BingResultsPage;
import com.vodafone.utils.BrowserActions;
import com.vodafone.utils.JsonUtils;
import com.vodafone.utils.LogsUtils;
import com.vodafone.utils.PropertiesUtils;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestNGListeners.class)
public class BingE2ETest {

    JsonUtils testData;

    int page2Count;

    // Tests
    @Test
    public void searchVodafone() {
        new BingHomePage(DriverManager.getDriver()).enterSearchTerm(testData.getJsonData("search.term"))
                .submitSearch()
                .assertSuccessfulSearch(testData.getJsonData("search.term"));

    }

    @Test(dependsOnMethods = "searchVodafone")
    public void validatePage1results()
    {
        new BingResultsPage(DriverManager.getDriver())
                .assertRelatedSearchSectionCount(2)
                .assertAtLeastTwoResultsContainKeywordInSnippet(testData.getJsonData("search.term"));
    }

    @Test(dependsOnMethods = "validatePage1results")
    public void goToPageTwo() {
         new BingResultsPage(DriverManager.getDriver())
                 .goToPage(2);
         page2Count= new BingResultsPage(DriverManager.getDriver()).getResultCount();
        LogsUtils.trace("Results count on Page 2 = " + page2Count);

    }



    @Test(dependsOnMethods = "goToPageTwo")
    public void comparePageTwoAndThreeResults() {
       new BingResultsPage(DriverManager.getDriver())
               .goToPage(3);
       int page3Count= new BingResultsPage(DriverManager.getDriver()).getResultCount();
        LogsUtils.trace("Results count on Page 3 = " + page3Count);
        Assert.assertTrue(page3Count>=page2Count,"Page 3 should have at least as many results as Page 2. Page2: \" + page2Count + \", Page3: \" + page3Count");

    }


    // Configuration
    @BeforeTest
    public void beforeTest() {
        testData = new JsonUtils("test-data");
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        DriverManager.createInstance(browserName);
        new BingHomePage(DriverManager.getDriver()).navigateToBingHomePage();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        BrowserActions.closeBrowser(DriverManager.getDriver());
    }
}