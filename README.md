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

##  Ejercicio 2: Pruebas de Aceptación con Cucumber

###  Escenarios BDD
1. **Inicio de sesión exitoso**
   ```gherkin
   Escenario: Inicio de sesion exitoso con credenciales validas
     Dado que estoy en la pagina de inicio de SauceDemo
     Cuando ingreso el nombre de usuario "standard_user" y la contraseña "secret_sauce"
     Y hago clic en el boton de inicio de sesion
     Entonces debo ser redirigido a la pagina de productos
     Y debo ver el titulo "PRODUCTS"
   ```

2. **Inicio de sesión fallido**
   ```gherkin
   Escenario: Inicio de sesion fallido con credenciales invalidas
     Dado que estoy en la pagina de inicio de SauceDemo
     Cuando ingreso el nombre de usuario "usuario_invalido" y la contraseña "contraseña_invalida"
     Y hago clic en el boton de inicio de sesion
     Entonces debo ver un mensaje de error
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

