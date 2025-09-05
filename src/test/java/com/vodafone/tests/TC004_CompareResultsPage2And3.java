package com.vodafone.tests;

import com.vodafone.Listners.TestNGListeners;
import com.vodafone.drivers.DriverManager;
import com.vodafone.pages.BingResultsPage;
import com.vodafone.utils.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class TC004_CompareResultsPage2And3 {

    int page2Count;

    @Test
    public void comparePageTwoAndThreeResults() {
        new BingResultsPage(DriverManager.getDriver())
                .goToPage(3);
        int page3Count = new BingResultsPage(DriverManager.getDriver()).getResultCount();
        LogsUtils.info("Results count on Page 3 = " + page3Count);
        Assert.assertTrue(page3Count >= page2Count, "Page 3 should have at least as many results as Page 2. Page2: \" + page2Count + \", Page3: \" + page3Count");

    }
}

