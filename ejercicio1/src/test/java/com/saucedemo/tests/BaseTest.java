package com.saucedemo.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriverService;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeAll
    public static void setupClass() {
        try {
            // Ruta al controlador de Microsoft Edge
            String edgeDriverPath = "C:\\\\Users\\\\lucaf\\\\OneDrive\\\\Escritorio\\\\Luca\\\\Sistemas\\\\Prueba de Software\\\\tp3\\\\drivers\\\\msedgedriver.exe";
            System.out.println("Configurando controlador en: " + edgeDriverPath);
            
            // Verificar que el archivo existe
            File driverFile = new File(edgeDriverPath);
            if (!driverFile.exists()) {
                throw new RuntimeException("No se encontró el controlador en: " + edgeDriverPath);
            }
            
            // Configurar la propiedad del sistema
            System.setProperty("webdriver.edge.driver", edgeDriverPath);
            
            // Configuración inicial del navegador
            
            System.out.println("Configuración completada correctamente");
            
        } catch (Exception e) {
            System.err.println("Error en la configuración inicial: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @BeforeEach
    public void setUp() {
        try {
            System.out.println("\n=== Iniciando configuración de prueba ===");
            
            // 1. Configuración de opciones de Edge
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
            
            // Configuración específica para Edge
            options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
            
            // Configuración del servicio
            System.setProperty("webdriver.edge.verboseLogging", "true");
            
            // Configuración del servicio con tiempo de espera extendido
            EdgeDriverService service = new EdgeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\Users\\lucaf\\OneDrive\\Escritorio\\Luca\\Sistemas\\Prueba de Software\\tp3\\drivers\\msedgedriver.exe"))
                .withLogOutput(System.out)
                .withVerbose(true)
                .build();
            
            // 2. Inicialización del navegador
            System.out.println("Iniciando Microsoft Edge...");
            long startTime = System.currentTimeMillis();
            
            // Iniciar el navegador con tiempo de espera extendido
            driver = new EdgeDriver(service, options);
            
            long endTime = System.currentTimeMillis();
            System.out.println("Tiempo de inicio: " + (endTime - startTime) + " ms");
            
            // 3. Configuración de timeouts
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            
            System.out.println("=== Navegador iniciado correctamente ===\n");
            
        } catch (Exception e) {
            System.err.println("\n=== ERROR al iniciar el navegador ===");
            System.err.println("Mensaje: " + e.getMessage());
            System.err.println("Causa: " + (e.getCause() != null ? e.getCause().getMessage() : "N/A"));
            e.printStackTrace();
            
           
            try {
                Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
                Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
                Thread.sleep(2000); 
            } catch (Exception ex) {
                System.err.println("No se pudieron cerrar los procesos de Edge: " + ex.getMessage());
            }
            
            throw new RuntimeException("No se pudo iniciar el navegador. Asegúrate de que Edge esté instalado en la ruta predeterminada y que la versión del controlador coincida con la versión de Edge.", e);
        }
    }

    @AfterEach
    public void afterEachTest(TestInfo testInfo) {
        // Método vacío después de cada prueba
        // Se puede usar para limpieza si es necesario
    }

    @AfterAll
    public static void tearDownClass() {
        try {
            System.out.println("\n=== Finalizando pruebas ===");
            
            if (driver != null) {
                System.out.println("Cerrando el navegador...");
                driver.quit();
                System.out.println("Navegador cerrado correctamente");
            }
            
            // Forzar el cierre de cualquier proceso de Edge o msedgedriver que haya quedado
            try {
                Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
                Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
                System.out.println("Procesos de Edge finalizados");
            } catch (Exception e) {
                System.err.println("Error al finalizar procesos: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Error durante el cierre: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver = null;
            System.out.println("=== Pruebas finalizadas ===\n");
        }
    }
}