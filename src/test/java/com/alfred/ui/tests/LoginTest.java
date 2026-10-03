package com.alfred.ui.tests;

import com.alfred.ui.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Login")
class LoginTest extends BaseTest {

    private static final String VALID_USER = "tomsmith";
    private static final String VALID_PASSWORD = "SuperSecretPassword!";

    @Test
    @DisplayName("Valid credentials open the secure area")
    void validLogin() {
        LoginPage login = new LoginPage(driver).open().loginAs(VALID_USER, VALID_PASSWORD);

        assertTrue(login.flashMessage().contains("You logged into a secure area!"));
        assertTrue(login.isOnSecureArea());
    }

    @ParameterizedTest(name = "user=''{0}'' password=''{1}'' -> {2}")
    @CsvSource({
            "wronguser, SuperSecretPassword!, Your username is invalid!",
            "tomsmith,  wrongpassword,        Your password is invalid!",
            "'',        '',                   Your username is invalid!",
            "TOMSMITH,  SuperSecretPassword!, Your username is invalid!"
    })
    @DisplayName("Invalid credentials show an error")
    void invalidLogin(String user, String password, String expectedError) {
        LoginPage login = new LoginPage(driver).open().loginAs(user, password);

        assertTrue(login.flashMessage().contains(expectedError),
                "Expected error: " + expectedError + " but was: " + login.flashMessage());
        assertFalse(login.isOnSecureArea());
    }

    @Test
    @DisplayName("Logout returns to the login page")
    void logout() {
        LoginPage login = new LoginPage(driver).open().loginAs(VALID_USER, VALID_PASSWORD).logout();

        assertTrue(login.flashMessage().contains("You logged out of the secure area!"));
    }
}
