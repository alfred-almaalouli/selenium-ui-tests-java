package com.alfred.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/** Checkboxes, dropdown and dynamically added elements. */
public class FormElementsPage extends BasePage {

    private final By checkboxes = By.cssSelector("#checkboxes input[type='checkbox']");
    private final By dropdown = By.id("dropdown");
    private final By addButton = By.cssSelector("button[onclick='addElement()']");
    private final By deleteButtons = By.cssSelector("#elements button");

    public FormElementsPage(WebDriver driver) {
        super(driver);
    }

    public FormElementsPage openCheckboxes() {
        open("/checkboxes");
        return this;
    }

    public FormElementsPage openDropdown() {
        open("/dropdown");
        return this;
    }

    public FormElementsPage openAddRemove() {
        open("/add_remove_elements/");
        return this;
    }

    public List<WebElement> checkboxes() {
        visible(checkboxes);
        return driver.findElements(checkboxes);
    }

    public void toggleCheckbox(int index) {
        checkboxes().get(index).click();
    }

    public String selectOption(String visibleText) {
        Select select = new Select(visible(dropdown));
        select.selectByVisibleText(visibleText);
        return select.getFirstSelectedOption().getText();
    }

    public void addElements(int count) {
        for (int i = 0; i < count; i++) {
            click(addButton);
        }
    }

    public void removeFirstElement() {
        click(deleteButtons);
    }

    public int numberOfAddedElements() {
        return driver.findElements(deleteButtons).size();
    }
}
