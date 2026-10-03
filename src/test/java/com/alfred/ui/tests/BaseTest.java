package com.alfred.ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Starts a fresh Chrome browser for every test and closes it afterwards.
 * If a test fails, a screenshot is saved to target/screenshots.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @RegisterExtension
    AfterTestExecutionCallback screenshotOnFailure = context -> {
        if (context.getExecutionException().isPresent() && driver != null) {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Path folder = Path.of("target", "screenshots");
            try {
                Files.createDirectories(folder);
                Files.write(folder.resolve(context.getDisplayName().replaceAll("[^\\w-]", "_") + ".png"), png);
            } catch (IOException e) {
                System.err.println("Could not save screenshot: " + e.getMessage());
            }
        }
    };

    @BeforeEach
    void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        if (!Boolean.getBoolean("headed")) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1366,900", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
