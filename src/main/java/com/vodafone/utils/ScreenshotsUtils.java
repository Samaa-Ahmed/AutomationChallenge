package com.vodafone.utils;

import com.vodafone.drivers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

public class ScreenshotsUtils {
    private ScreenshotsUtils() {
    }

    public static final String SCREENSHOTS_PATH = "test-outputs/Screenshots";


    public static void captureScreenshot(String screenshotName)
    {
        try {
        File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File screenShotsFile = new File(SCREENSHOTS_PATH + "/" + screenshotName + ".png");
        FileUtils.copyFile(screenshot, screenShotsFile);
        AllureUtils.attachScreenshotToAllure(screenshotName, screenShotsFile.getPath());

        } catch (Exception e) {
            LogsUtils.error("Failed to capture screenshot: " + e.getMessage());
        }

    }
}


