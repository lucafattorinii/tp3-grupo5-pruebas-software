# TP 3 – Pruebas funcionales y de aceptación con Selenium

##  Información del Trabajo Práctico
- **Universidad**: Universidad Nacional de Lanús
- **Carrera**: Licenciatura en Sistemas
- **Asignatura**: Pruebas de Software
- **Año**: 2025
- **Docente a cargo**: Eduardo Diez
- **Docente**: Pablo San Román

##  Integrantes del grupo 5
- **Fattorini, Luca** - lucafattorini.dev@gmail.com
- **Melgarejo Villalba, Pablo Eduardo** - edumelgarejo27@gmail.com
- **Turkula, Claudio Matías** - claudioturkula@gmail.com

##  Descripción
Este repositorio contiene la implementación del Trabajo Práctico N°3 de la materia Pruebas de Software. El mismo consiste en la realización de pruebas automatizadas para la aplicación web [SauceDemo](https://www.saucedemo.com/) utilizando Selenium WebDriver, JUnit y Cucumber.

##  Estructura del Proyecto
```
tp3/
├── ejercicio1/              # Pruebas funcionales con Selenium y JUnit
│   ├── src/test/java/
│   │   └── com/saucedemo/tests/
│   │       ├── BaseTest.java
│   │       └── SauceDemoTests.java
│   └── pom.xml
│
├── ejercicio2/              # Pruebas BDD con Cucumber
│   ├── src/test/java/
│   │   └── com/saucedemo/
│   │       ├── runners/CucumberTestSuite.java
│   │       ├── stepdefinitions/LoginStepDefinitions.java
│   │       └── utils/TestContext.java
│   ├── src/test/resources/features/
│   │   └── login.feature
│   └── pom.xml
│
└── drivers/                 # Controladores de navegador
    └── msedgedriver.exe
```

##  Ejercicio 1: Pruebas Funcionales con Selenium

###  Casos de Prueba Implementados
1. **Inicio de sesión exitoso**
   - Verifica el acceso con credenciales válidas
   - Valida la redirección a la página de productos

2. **Agregar producto al carrito**
   - Agrega un producto al carrito
   - Verifica que el contador del carrito se actualice

3. **Finalizar compra**
   - Completa el flujo de compra
   - Verifica la confirmación del pedido

###  Tecnologías Utilizadas
- Java 11
- Selenium WebDriver 4.10.0
- JUnit 5
- Maven

###  Cómo Ejecutar
```bash
cd ejercicio1
mvn clean test
```

##  Ejercicio 2: Pruebas BDD con Cucumber

###  Nota sobre el idioma

Las pruebas BDD se implementaron en ingles porque el parser de Gherkin de Cucumber mostraba problemas al interpretar las palabras clave en español, incluso con la directiva `# language: es` al inicio del archivo feature. 

Para garantizar la estabilidad y confiabilidad de las pruebas, se decidió utilizar el inglés como idioma predeterminado para:
- Palabras clave de Gherkin (Given, When, Then, etc.)
- Nombres de métodos en las definiciones de pasos
- Mensajes de aserción y logs

###  Escenarios BDD
1. **Inicio de sesión exitoso**
   ```gherkin
   Scenario: Successful login with valid credentials
     Given I am on the SauceDemo login page
     When I enter username "standard_user" and password "secret_sauce"
     And I click the login button
     Then I should be redirected to the products page
     And I should see the title "Products"
   ```

2. **Inicio de sesión fallido**
   ```gherkin
   Scenario: Failed login with invalid credentials
     Given I am on the SauceDemo login page
     When I enter username "invalid_user" and password "invalid_password"
     And I click the login button
     Then I should see an error message
   ```

###  Tecnologías Utilizadas
- Java 11
- Selenium WebDriver 4.10.0
- Cucumber 7.11.1
- JUnit 5
- Maven

###  Cómo Ejecutar
```bash
cd ejercicio2
mvn clean test
```

##  Requisitos del Sistema
- Java JDK 11 o superior
- Maven 3.6.3 o superior
- Microsoft Edge (ultima versión estable)
- Conexión a Internet

##  Configuración
1. Clonar el repositorio
   ```bash
   git clone [https://github.com/lucafattorinii/tp3-grupo5-pruebas-software.git]
   cd tp3-grupo5-pruebas-software
   ```

2. Configurar las variables de entorno (opcional)
   - Asegurarse de que JAVA_HOME esté configurado
   - Verificar que Maven esté en el PATH

3. Instalar dependencias
   ```bash
   mvn clean install
   ```

##  Reportes
Los reportes de las pruebas se generan en:
- **Ejercicio 1**: `ejercicio1/target/surefire-reports/`
- **Ejercicio 2**: `ejercicio2/target/cucumber-reports/`

##  Notas Adicionales
- Las pruebas están configuradas para ejecutarse en Microsoft Edge
- Se requiere el controlador de Edge (msedgedriver) en la carpeta `drivers/`
- Las credenciales de prueba están configuradas en los archivos de prueba

##  Documentación Adicional
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Cucumber Documentation](https://cucumber.io/docs/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

