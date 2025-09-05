package com.vodafone.tests;

import com.vodafone.Listners.TestNGListeners;
import com.vodafone.drivers.DriverManager;
import com.vodafone.pages.BingHomePage;
import com.vodafone.utils.BrowserActions;
import com.vodafone.utils.JsonUtils;
import com.vodafone.utils.PropertiesUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.*;

@Listeners(TestNGListeners.class)
public class TC001_SearchNavigation
{
    JsonUtils testData;

    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void searchVodafone() {
        new BingHomePage(DriverManager.getDriver()).enterSearchTerm(testData.getJsonData("search.term"))
                .submitSearch()
                .assertSuccessfulSearch(testData.getJsonData("search.term"));

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
