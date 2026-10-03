package com.alfred.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage extends BasePage {

    private final By startButton = By.cssSelector("#start button");
    private final By finishText = By.cssSelector("#finish h4");

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    /** example 1: element is hidden, example 2: element is added to the page later */
    public DynamicLoadingPage open(int example) {
        open("/dynamic_loading/" + example);
        return this;
    }

    public String startAndWaitForResult() {
        click(startButton);
        return text(finishText);
    }
}
