package com.alfred.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TablesPage extends BasePage {

    private final By lastNameHeader = By.cssSelector("#table1 thead th:nth-child(1)");
    private final By lastNameCells = By.cssSelector("#table1 tbody tr td:nth-child(1)");
    private final By dueCells = By.cssSelector("#table1 tbody tr td:nth-child(4)");

    public TablesPage(WebDriver driver) {
        super(driver);
    }

    public TablesPage open() {
        open("/tables");
        return this;
    }

    public void sortByLastName() {
        click(lastNameHeader);
    }

    public List<String> lastNames() {
        visible(lastNameCells);
        return driver.findElements(lastNameCells).stream().map(WebElement::getText).toList();
    }

    /** "$50.00" -> 50.00 */
    public List<Double> amountsDue() {
        visible(dueCells);
        return driver.findElements(dueCells).stream()
                .map(cell -> Double.parseDouble(cell.getText().replace("$", "")))
                .toList();
    }
}
