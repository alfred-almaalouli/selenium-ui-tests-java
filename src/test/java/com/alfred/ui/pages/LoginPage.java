package com.alfred.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By logoutButton = By.cssSelector("a[href='/logout']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        open("/login");
        return this;
    }

    public LoginPage loginAs(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
        return this;
    }

    public String flashMessage() {
        return text(flashMessage);
    }

    public LoginPage logout() {
        click(logoutButton);
        return this;
    }

    public boolean isOnSecureArea() {
        return driver.getCurrentUrl().endsWith("/secure");
    }
}
