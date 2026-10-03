package com.alfred.ui.tests;

import com.alfred.ui.pages.FormElementsPage;
import com.alfred.ui.pages.TablesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Form elements and tables")
class FormElementsTest extends BaseTest {

    @Test
    @DisplayName("Checkboxes change their state when clicked")
    void checkboxes() {
        FormElementsPage page = new FormElementsPage(driver).openCheckboxes();
        assertFalse(page.checkboxes().get(0).isSelected());
        assertTrue(page.checkboxes().get(1).isSelected());

        page.toggleCheckbox(0);
        page.toggleCheckbox(1);

        assertTrue(page.checkboxes().get(0).isSelected());
        assertFalse(page.checkboxes().get(1).isSelected());
    }

    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"Option 1", "Option 2"})
    @DisplayName("Dropdown option can be selected")
    void dropdown(String option) {
        assertEquals(option, new FormElementsPage(driver).openDropdown().selectOption(option));
    }

    @Test
    @DisplayName("Elements can be added and removed")
    void addAndRemoveElements() {
        FormElementsPage page = new FormElementsPage(driver).openAddRemove();

        page.addElements(3);
        assertEquals(3, page.numberOfAddedElements());

        page.removeFirstElement();
        assertEquals(2, page.numberOfAddedElements());
    }

    @Test
    @DisplayName("Table can be sorted by last name")
    void sortTableByLastName() {
        TablesPage table = new TablesPage(driver).open();

        table.sortByLastName();
        List<String> names = table.lastNames();
        List<String> expected = new ArrayList<>(names);
        Collections.sort(expected);

        assertEquals(expected, names);
    }

    @Test
    @DisplayName("Table amounts are valid money values")
    void tableAmounts() {
        List<Double> amounts = new TablesPage(driver).open().amountsDue();

        assertEquals(4, amounts.size());
        assertTrue(amounts.stream().allMatch(a -> a >= 0));
    }
}
