[![CI](https://github.com/pkalogeropoulos/Selenium_Java/actions/workflows/ci.yml/badge.svg)](https://github.com/pkalogeropoulos/Selenium_Java/actions/workflows/ci.yml)
![Selenium](https://img.shields.io/badge/Selenium-4.34.0-green)
![Java](https://img.shields.io/badge/Java-21-blue)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-orange)
![Maven](https://img.shields.io/badge/Maven-3.9.x-blue)
[![codecov](https://codecov.io/github/pkalogeropoulos/Selenium_Java/graph/badge.svg?token=2G2ES2C91B)](https://codecov.io/github/pkalogeropoulos/Selenium_Java)
![WebDriverManager](https://img.shields.io/badge/WebDriverManager-latest-blue)
![Checkstyle](https://img.shields.io/badge/Checkstyle-passing-brightgreen)
![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)

Demo Web Shop Automation Framework (Selenium • Java • TestNG)

This repository contains a modern, scalable UI test automation framework built for the Demo Web Shop website:

👉 https://demowebshop.tricentis.com/

The project showcases clean architecture, Fluent Page Object Model (POM), component-based design, configurable environments, TestNG test orchestration, Allure reporting, and a fully modular structure that reflects industry-standard best practices.

This framework is designed to be portfolio-quality for SDET / QA Automation Engineer roles.


<h1>Features</h1>
✅ Selenium WebDriver (Java)

Modern WebDriver design with smart waits, JS utilities, scrolling helpers, and robust element handling.
<p>

✅ Fluent Page Object Model (POM)

* Clean, chainable APIs

* Highly readable tests

* No PageFactory

* Strong typing via generics (BasePage<T>)

* Reusable UI components (HeaderBar, HeaderMenuBar, etc.)
<p></p>

✅ Component-based architecture

Shared UI elements like the site header and navigation menu are modeled as reusable components — not duplicated across pages.

Examples:

* HeaderBar (Register/Login/Logout/Cart/Wishlist)

* HeaderMenuBar (Books, Computers, Electronics, etc.)


✅ Full site coverage

POMs for all major pages:

* LoginPage

* RegisterPage

* HomePage

* ComputersPage / Desktops / Notebooks / Accessories

* ShoppingCartPage

* WishlistPage

* MyAccountPage

* CustomerInfoPage

* CheckoutPage (one-page checkout)

* OrderCompletedPage (Thank You page)

  
✅ Configurable environments (AppConfig)

* Automatic loading from src/main/resources/config-*.properties:

* config-dev.properties

* config-qa.properties

* config-prod.properties

* Supports -Denv=qa to switch environments dynamically.

✅ TestBase with WebDriver management

* Central WebDriver creation

* Before/After suite & method

* Automatic screenshot capture on test failure

* Allure attachment integration


✅ TestNG test orchestration

* testng.xml under src/test/resources

* Suite, parallel tests, packages organization

✅ Allure reporting

* Step annotations

* Test metadata (Epic, Feature, Story, Severity)

* Automatic screenshot attachments

* Pretty HTML report (via Allure)

## 📁 Project Structure
```
project-root/
│
├── pom.xml
├── README.md
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com.example.demowebshop/
│ │ │ | └── config/
│ │ │ | └── driver/
│ │ │ | └── enums/
│ │ │ | └── factories/
│ │ │ | └── model/
│ │ │ | └── pages/
│ │ │ | └── components/
│ │ │ | └── ui/
│ │ │
│ │ └── resources/
│ │ │ | └── config-dev.properties
│ │ │ | └── config-production.properties
│ │ │ | └── config-qa.properties
│ │ │ | └── config-staging.properties
│ │
│ └── test/
│ ├── java/
│ │ └── com.example.demowebshop.tests/
│ │ | └── base/
│ │ | └── search/
│ │ 
│ └── resources/
│ | └── tests.xml
```

## 🧱 Architecture Overview
Every page extends:
```
public abstract class BasePage<T extends BasePage<T>> {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected T self() {
        return (T) this;
    }

    public HeaderBar<T> header()   { return new HeaderBar<>(driver, self()); }
    public HeaderMenuBar<T> menu() { return new HeaderMenuBar<>(driver, self()); }
}
```

## 🚀 How to run
```
mvn clean test
mvn clean test -Denv=staging -Dbrowser=chrome
```
Or you can execute: 
```
src/test/resources/tests.xml
```
