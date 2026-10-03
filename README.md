# Selenium UI Test Automation (Java)

![UI Tests](https://github.com/alfred-almaalouli/selenium-ui-tests-java/actions/workflows/tests.yml/badge.svg)

Automated browser tests in **Java** with **Selenium WebDriver**, **JUnit 5** and **Maven**, using the practice site [The Internet](https://the-internet.herokuapp.com). The tests run in headless Chrome in **GitHub Actions** on every push.

## What is tested

| Area | Checks |
|------|--------|
| Login | valid login, 4 invalid combinations (wrong user, wrong password, empty fields, wrong upper/lower case), logout |
| Dynamic content | content that appears after loading (hidden element and element added later) – handled with explicit waits, no `sleep()` |
| JavaScript dialogs | alert, confirm (OK / Cancel), prompt with text input |
| Form elements | checkboxes, dropdown options, adding and removing elements |
| Tables | sorting by column, reading and validating cell values |

## Tech stack

- Java 17
- Selenium WebDriver 4 (Selenium Manager handles the browser driver)
- JUnit 5 (parameterized tests with `@CsvSource` / `@ValueSource`)
- Maven
- GitHub Actions (headless Chrome, test reports and screenshots as artifacts)

## Project structure

```
src/test/java/com/alfred/ui/
  pages/                  Page Object Model
    BasePage.java         shared actions with explicit waits
    LoginPage.java
    DynamicLoadingPage.java
    AlertsPage.java
    FormElementsPage.java
    TablesPage.java
  tests/
    BaseTest.java         starts/stops Chrome, screenshot on failure
    LoginTest.java
    DynamicContentTest.java
    FormElementsTest.java
```

## Run the tests

```bash
mvn test                 # headless Chrome
mvn test -Dheaded=true   # watch the browser
```

## Design decisions

- **Page Object Model** – locators and actions are in `pages/`; tests only describe the scenario and the expected result.
- **Explicit waits** – every action waits until the element is visible or clickable (`WebDriverWait`), which makes the tests stable.
- **Fresh browser per test** – tests are independent and can run in any order.
- **Screenshot on failure** – a JUnit 5 extension saves a screenshot to `target/screenshots/` when a test fails; CI uploads it as an artifact.
- **Data-driven tests** – login errors and dropdown options are tested with parameterized tests.

## Author

Alfred Al Maalouli – [GitHub](https://github.com/alfred-almaalouli)
