package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class TestContext {
    private WebDriver driver;
    private static TestContext instance;

    private TestContext() {
        try {
            System.out.println("\n=== Inicializando TestContext ===");
            
            // 1. Configuracion del controlador
            String edgeDriverPath = "C:\\\\Users\\\\lucaf\\\\OneDrive\\\\Escritorio\\\\Luca\\\\Sistemas\\\\Prueba de Software\\\\tp3\\\\drivers\\\\msedgedriver.exe";
            System.out.println("Ruta del controlador: " + edgeDriverPath);
            
            // Verificar que el archivo existe
            java.io.File driverFile = new java.io.File(edgeDriverPath);
            if (!driverFile.exists()) {
                throw new RuntimeException("No se encontró el controlador en: " + edgeDriverPath);
            }
            
            // Configurar la propiedad del sistema
            System.setProperty("webdriver.edge.driver", edgeDriverPath);
            System.setProperty("webdriver.edge.verboseLogging", "true");
            
            // 2. Configuracion de opciones de Edge
            EdgeOptions options = new EdgeOptions();
            
            // Configuracion basica
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            
            // Configuracion para evitar problemas comunes
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-software-rasterizer");
            
            // Configuracion de rendimiento
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-infobars");
            
            
            // 3. Inicializacion del navegador
            System.out.println("Iniciando Microsoft Edge...");
            long startTime = System.currentTimeMillis();
            
            this.driver = new EdgeDriver(options);
            
            // Configurar timeouts
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            this.driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            
            System.out.println("EdgeDriver iniciado correctamente");
        } catch (Exception e) {
            System.err.println("Error al iniciar EdgeDriver: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        try {
            if (driver != null) {
                System.out.println("\n=== Cerrando navegador ===");
                driver.quit();
                System.out.println("Navegador cerrado correctamente");
                
                // Forzar el cierre de cualquier proceso de Edge o msedgedriver que haya quedado
                try {
                    Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
                    Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
                    System.out.println("Procesos de Edge finalizados");
                } catch (Exception e) {
                    System.err.println("Error al finalizar procesos: " + e.getMessage());
                }
                
                driver = null;
                instance = null;
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar el navegador: " + e.getMessage());
            e.printStackTrace();
        }
    }
}