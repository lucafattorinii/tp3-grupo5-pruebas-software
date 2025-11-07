package com.saucedemo.stepdefinitions;

import com.saucedemo.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepDefinitions {
    private final TestContext testContext;

    public LoginStepDefinitions() {
        this.testContext = TestContext.getInstance();
    }

    @Given("que estoy en la página de inicio de SauceDemo")
    public void queEstoyEnLaPáginaDeInicioDeSauceDemo() throws InterruptedException {
        testContext.getDriver().get("https://www.saucedemo.com/");
        Thread.sleep(2000); // Pausa de 2 segundos
    }

    @When("ingreso el nombre de usuario {string} y la contraseña {string}")
    public void ingresoElNombreDeUsuarioYLaContraseña(String username, String password) throws InterruptedException {
        testContext.getDriver().findElement(By.id("user-name")).sendKeys(username);
        Thread.sleep(1000); // Pausa de 1 segundo
        testContext.getDriver().findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000); // Pausa de 1 segundo
    }

    @And("hago clic en el botón de inicio de sesión")
    public void hagoClicEnElBotónDeInicioDeSesión() throws InterruptedException {
        testContext.getDriver().findElement(By.id("login-button")).click();
        Thread.sleep(2000); // Pausa de 2 segundos
    }

    @Then("debo ser redirigido a la página de productos")
    public void deboSerRedirigidoALaPáginaDeProductos() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(testContext.getDriver(), Duration.ofSeconds(10));
        WebElement productsTitle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        );
        Thread.sleep(1000); // Pausa de 1 segundo
        assertTrue(productsTitle.isDisplayed());
        assertTrue(testContext.getDriver().getCurrentUrl().endsWith("/inventory.html"));
        Thread.sleep(2000); // Pausa de 2 segundos
    }

    @And("debo ver el título {string}")
    public void deboVerElTítulo(String titulo) throws InterruptedException {
        WebElement titleElement = testContext.getDriver().findElement(By.className("title"));
        Thread.sleep(1000); // Pausa de 1 segundo
        assertEquals(titulo, titleElement.getText());
        Thread.sleep(1000); // Pausa de 1 segundo
    }

    @Then("debo ver un mensaje de error que diga {string}")
    public void deboVerUnMensajeDeErrorQueDiga(String mensajeError) throws InterruptedException {
        WebElement errorElement = testContext.getDriver().findElement(By.cssSelector("h3[data-test='error']"));
        Thread.sleep(1000); // Pausa de 1 segundo
        assertEquals(mensajeError, errorElement.getText());
        Thread.sleep(2000); // Pausa de 2 segundos
    }
}