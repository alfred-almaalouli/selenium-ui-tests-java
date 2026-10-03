package com.alfred.ui.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertsPage extends BasePage {

    private final By alertButton = By.cssSelector("button[onclick='jsAlert()']");
    private final By confirmButton = By.cssSelector("button[onclick='jsConfirm()']");
    private final By promptButton = By.cssSelector("button[onclick='jsPrompt()']");
    private final By result = By.id("result");

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    public AlertsPage open() {
        open("/javascript_alerts");
        return this;
    }

    private Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String acceptAlert() {
        click(alertButton);
        waitForAlert().accept();
        return text(result);
    }

    public String answerConfirm(boolean accept) {
        click(confirmButton);
        Alert alert = waitForAlert();
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return text(result);
    }

    public String answerPrompt(String answer) {
        click(promptButton);
        Alert alert = waitForAlert();
        alert.sendKeys(answer);
        alert.accept();
        return text(result);
    }
}
