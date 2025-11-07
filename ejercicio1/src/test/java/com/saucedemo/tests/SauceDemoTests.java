package com.saucedemo.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SauceDemoTests extends BaseTest {
    
    private void login(String username, String password) {
        driver.get(BASE_URL);
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    @Order(1)
    @DisplayName("CP001 - Inicio de sesión exitoso")
    public void testLoginExitoso() {
        // Caso de prueba 1: Inicio de sesión exitoso
        login("standard_user", "secret_sauce");
        
        // Verificar que se redirige a la página de productos
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement productsTitle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        );
        
        assertEquals("Products", productsTitle.getText());
        assertTrue(driver.getCurrentUrl().endsWith("/inventory.html"));
    }

    @Test
    @Order(2)
    @DisplayName("CP002 - Agregar producto al carrito")
    public void testAgregarProductoAlCarrito() {
        // Caso de prueba 2: Agregar un producto al carrito
        login("standard_user", "secret_sauce");
        
        // Agregar un producto al carrito
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        
        // Verificar que el carrito muestra 1 articulo
        String cartBadgeText = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertEquals("1", cartBadgeText, "El carrito debería mostrar 1 artículo");
        
        // Ir al carrito y verificar que el producto esta presente
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement cartItem = driver.findElement(By.className("cart_item"));
        assertTrue(cartItem.isDisplayed(), "El producto debería estar visible en el carrito");
    }

    @Test
    @Order(3)
    @DisplayName("CP003 - Completar proceso de compra")
    public void testCheckout() throws InterruptedException {
        // Caso de prueba 3: Completar el proceso de compra
        login("standard_user", "secret_sauce");
        
        // Agregar producto y proceder al checkout
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        
        // Completar informacion de envio
        driver.findElement(By.id("first-name")).sendKeys("Usuario");
        driver.findElement(By.id("last-name")).sendKeys("Prueba");
        driver.findElement(By.id("postal-code")).sendKeys("1234");
        driver.findElement(By.id("continue")).click();
        
        // Finalizar compra
        WebElement finishButton = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        
        // Desplazar la pagina hasta el boton y hacer clic
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finishButton);
        Thread.sleep(500); // Pequeña pausa para asegurar el desplazamiento
        finishButton.click();
        
        // Verificar mensaje de compra exitosa
        WebElement completeHeader = driver.findElement(By.className("complete-header"));
        assertEquals("Thank you for your order!", completeHeader.getText(), 
            "Debería mostrarse el mensaje de compra exitosa");
    }
}