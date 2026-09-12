# Certificacion 2 - Tercer Parcial - SauceDemo Automation Framework

Framework de automatizacion para https://www.saucedemo.com con Selenium + JUnit + Cucumber y patron POM.

## Herramientas
- Selenium 4.47.0
- JUnit 6.1.3 (Jupiter) + Cucumber 7.34.7 (cucumber-java, cucumber-core, cucumber-junit)
- WebDriverManager 6.3.4
- ExtentReports 5.1.2 + extentreports-cucumber7-adapter 1.14.0 (reportes Html, Spark y Pdf)
- Java 17, Maven 3

## Estructura (clonada de selenium-pom 3)
```
src/main/java/pages/ (LoginPage, HomePage, YourCartPage, CheckoutYourInformationPage, CheckoutOverviewPage, CheckoutCompletePage)
src/main/java/utils/DriverManager.java
src/test/java/TestRunner.java (@RunWith Cucumber)
src/test/java/stepDefinitions/ (Hooks, CommonSteps, LoginSteps, HomeSteps, YourCartSteps, CheckoutYourInformationSteps, CheckoutOverviewSteps, CheckoutCompleteSteps, BugsSteps)
src/test/resources/ (login.feature, home.feature, checkout.feature, bugs.feature, extent.properties)
```

## Escenarios (5 bugs adaptados - bugs.feature)
1. **Checkout sin productos** - verifica checkout con carrito vacio
2. **Reset App State sin refresh en carrito** - DataTable 2 productos, reset, verifica badge y DOM sin refresh
3. **Estado botones tras reset** - DataTable productos, remove/add, reset, verifica Remove->Add to cart
4. **Logout y 3 backs Epic sadface** - DataTable 3 errores, 3 navigate().back()
5. **Checkout datos aleatorios sin limite** - Scenario Outline + DataTable, sin validacion postal, avanza a overview

Conceptos: `Background`, `Scenario Outline` (Examples), `DataTable`, `Hooks` (@Before/@After con screenshot), `Selectors` (By.id/className/cssSelector/xpath), `Assertions`, `POM`.

## Ejecucion
```bash
# JDK 17
set JAVA_HOME=C:\Program Files\Java\jdk-17
mvn clean test              # TestRunner -> 21 tests (16 originales + 5 bugs)
mvn test -Dcucumber.filter.tags="@checkoutVacio"  # solo bugs
```

Reportes Extent:
- `test-output/HtmlReport/Html-Report.html`
- `test-output/SparkReport/Spark-Report.html`
- `test-output/PdfReport/Pdf-Report.pdf`

## Autor
Suikasama123 - danielochoa1@upb.edu - UPB Certificacion 2
