package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class TestContext {
    private WebDriver driver;
    private static TestContext instance;

    private TestContext() {
        // Constructor privado para evitar instanciación directa
        initializeDriver();
    }
    
    public void initializeDriver() {
        try {
            // Si ya hay un driver en ejecución, lo cerramos
            if (this.driver != null) {
                this.driver.quit();
                this.driver = null;
            }
            
            System.out.println("\n=== Inicializando TestContext ===");
            
            // 1. Configuración del controlador
            String projectPath = new java.io.File("").getAbsolutePath();
            String edgeDriverPath = projectPath + "\\..\\..\\drivers\\msedgedriver.exe";
            System.out.println("Ruta del controlador: " + edgeDriverPath);
            
            // Verificar que el archivo existe
            java.io.File driverFile = new java.io.File(edgeDriverPath);
            if (!driverFile.exists()) {
                throw new RuntimeException("No se encontró el controlador en: " + edgeDriverPath + 
                                       "\nAsegurate de que el archivo msedgedriver.exe este en la carpeta 'drivers' en la raíz del proyecto.");
            }
            
            // Configurar la propiedad del sistema
            System.setProperty("webdriver.edge.driver", edgeDriverPath);
            System.setProperty("webdriver.edge.verboseLogging", "true");
            
            // 2. Configuración de opciones de Edge
            EdgeOptions options = new EdgeOptions();
            
            // Configuración básica
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            
            // Configuración para evitar problemas comunes
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-software-rasterizer");
            
            // Configuración de rendimiento
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-infobars");
            
            // 3. Inicializacion del navegador
            System.out.println("Iniciando Microsoft Edge...");
            long startTime = System.currentTimeMillis();
            
            this.driver = new EdgeDriver(options);
            
            // Configuraciones adicionales del navegador
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            this.driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            this.driver.manage().deleteAllCookies();
            
            System.out.println("EdgeDriver iniciado correctamente");
        } catch (Exception e) {
            System.err.println("Error al iniciar EdgeDriver: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static synchronized TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        } else if (instance.getDriver() == null) {
            // Si el driver fue cerrado, creamos una nueva instancia
            instance = new TestContext();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Navegador cerrado correctamente");
            } catch (Exception e) {
                System.err.println("Error al cerrar el navegador: " + e.getMessage());
            } finally {
                driver = null;
                instance = null;
                
                // Forzar el cierre de cualquier proceso de Edge o msedgedriver que haya quedado
                try {
                    Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
                    Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
                    System.out.println("Procesos de Edge finalizados");
                } catch (Exception e) {
                    System.err.println("Error al finalizar procesos: " + e.getMessage());
                }
            }
        }
    }
}