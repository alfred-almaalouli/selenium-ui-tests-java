package com.alfred.ui.tests;

import com.alfred.ui.pages.AlertsPage;
import com.alfred.ui.pages.DynamicLoadingPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Dynamic content and alerts")
class DynamicContentTest extends BaseTest {

    @ParameterizedTest(name = "example {0}")
    @ValueSource(ints = {1, 2})
    @DisplayName("Text appears after loading (explicit wait, no sleep)")
    void dynamicLoading(int example) {
        String result = new DynamicLoadingPage(driver).open(example).startAndWaitForResult();

        assertEquals("Hello World!", result);
    }

    @Test
    @DisplayName("JS alert can be accepted")
    void acceptAlert() {
        assertEquals("You successfully clicked an alert", new AlertsPage(driver).open().acceptAlert());
    }

    @Test
    @DisplayName("JS confirm: OK and Cancel")
    void confirmDialog() {
        AlertsPage alerts = new AlertsPage(driver).open();

        assertEquals("You clicked: Ok", alerts.answerConfirm(true));
        assertEquals("You clicked: Cancel", alerts.answerConfirm(false));
    }

    @Test
    @DisplayName("JS prompt returns the entered text")
    void promptDialog() {
        assertEquals("You entered: Alfred", new AlertsPage(driver).open().answerPrompt("Alfred"));
    }
}
