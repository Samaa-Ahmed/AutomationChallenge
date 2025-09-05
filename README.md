# 🧪 Automation Challenge
Automate end-to-end Bing search scenarios, including search, autocomplete suggestions, related searches, and navigation across result pages.
This repository contains an automation framework developed using Java, Maven, TestNG, and Allure. It is designed to automate web application testing, providing insights into test execution and results.

# 🛠️ Technologies used
Java: Programming language for test scripts.

Maven: Build automation tool.

TestNG: Testing framework for running tests.

Allure: Reporting tool for generating test reports.

GitHub Actions: CI/CD pipeline for automating workflows.

# 🔧 Project Structure
<img src="https://github.com/user-attachments/assets/bd7b27c2-2277-4eb7-9c53-11a40aa6c2a1" alt="Automation Framework Diagram" width="600" />


# ✅ Highlights
Modular and reusable test scripts

Data-driven testing support

Cross-browser testing capability

Detailed execution reports
# Components
1.	DriverManager / BrowserFactory
o	Initializes WebDriver instances for Chrome, Edge, or Firefox.
o	Configurable options: headless mode, custom user-agent, disable automation flags, notifications suppression.
2.	Page Objects
o	BingHomePage: Handles search input, autocomplete suggestions.
o	BingResultsPage: Handles results listing, related searches, page navigation.
o	Uses locators (By) to identify elements and methods to perform actions.
3.	ElementActions & BrowserActions
o	Reusable actions like click, jsClick, sendKeys, scrollToElement.
o	Wait utilities (WebDriverWait) ensure elements are visible or clickable.
4.	Validations & Logs
o	Assertion utilities to validate search results, related searches, or page counts.
o	Logs captured via LogsUtils and attached to Allure reports.
5.	TestNG Test Suites
o	Structured as classes with independent test methods.
o	Supports parallel execution by classes or tests.
o	XML configuration allows control over thread count and execution type.

# Features
•	Hybrid Automation Framework: Combines reusable methods (modular) and data-driven approach.
•	JavaScript-based element interaction: Handles dynamic or stale elements using jsClick and scroll methods.
•	Cross-browser support: Chrome, Edge, optionally Firefox.
•	Comprehensive reporting: Allure integration for detailed logs and screenshots.
•	Parallel execution: Reduces test runtime significantly.


# Limitations
•	Some sites (like Bing) may trigger bot detection / CAPTCHA, requiring manual intervention.
•	Shared WebDriver instances must be thread-safe for parallel runs.
•	Certain dynamic elements may still throw StaleElementReferenceException, requiring careful wait strategies.
•	Headless execution might behave slightly differently than full browsers.

