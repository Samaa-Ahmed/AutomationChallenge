package com.vodafone.tests;

import com.vodafone.Listners.TestNGListeners;
import com.vodafone.drivers.DriverManager;
import com.vodafone.pages.BingResultsPage;
import com.vodafone.utils.LogsUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class TC003_CountResultsOnPage2 {

    int page2Count;

    @Test
    public void goToPageTwo() {
        new BingResultsPage(DriverManager.getDriver())
                .goToPage(2);
        page2Count = new BingResultsPage(DriverManager.getDriver()).getResultCount();
        LogsUtils.info("Results count on Page 2 = " + page2Count);

    }
}
