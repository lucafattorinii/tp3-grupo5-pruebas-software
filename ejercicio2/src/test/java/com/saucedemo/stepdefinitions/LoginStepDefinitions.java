package com.saucedemo.stepdefinitions;

import com.saucedemo.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepDefinitions {
    private final TestContext testContext;

    public LoginStepDefinitions() {
        this.testContext = TestContext.getInstance();
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() throws InterruptedException {
        // If there's already a driver running, close it
        if (testContext.getDriver() != null) {
            testContext.getDriver().quit();
        }
        // Initialize a new driver
        testContext.initializeDriver();
        // Navigate to the login page
        testContext.getDriver().get("https://www.saucedemo.com/");
        Thread.sleep(2000); // 2 second pause
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) throws InterruptedException {
        WebElement usernameField = testContext.getDriver().findElement(By.id("user-name"));
        WebElement passwordField = testContext.getDriver().findElement(By.id("password"));
        
        usernameField.clear();
        usernameField.sendKeys(username);
        Thread.sleep(1000);
        
        passwordField.clear();
        passwordField.sendKeys(password);
        Thread.sleep(1000);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() throws InterruptedException {
        WebElement loginButton = testContext.getDriver().findElement(By.id("login-button"));
        loginButton.click();
        Thread.sleep(2000);
    }

    @Then("I should be redirected to the products page")
    public void iShouldBeRedirectedToTheProductsPage() {
        String currentUrl = testContext.getDriver().getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"), "Not redirected to the products page");
    }

    @And("I should see the title {string}")
    public void iShouldSeeTheTitle(String title) {
        WebElement titleElement = testContext.getDriver().findElement(By.cssSelector(".title"));
        assertEquals(title, titleElement.getText(), "Title does not match expected value");
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        WebElement errorElement = testContext.getDriver().findElement(By.cssSelector("[data-test='error']"));
        assertTrue(errorElement.isDisplayed(), "Error message was not displayed");
        
        // Verify that the error message contains the expected text
        String errorText = errorElement.getText();
        assertTrue(errorText.contains("Username and password do not match") || 
                  errorText.contains("Epic sadface: Username and password do not match a verified user"), 
                  "Error message text is not as expected");
    }
}