POM Test Automation Framework - Login Scenario

This is a Java-based test automation framework designed to simulate a login scenario 
using Selenium WebDriver, TestNG, and Maven as the build tool. Please note that you are not required to
download any driver as the WebDriverManager library used in this project implementation handles the download,
setup and management of the drivers.

Prerequisites

Before you can run this project, you will need to have the following installed:

Java Development Kit (JDK) version 8 or later
Integrated Development Environment (IDE) such as IntelliJ IDEA.
Maven
Git


Getting Started

To get started with this project, follow these steps:

Clone the repository to your local machine using Git: git clone https://github.com/your-username/your-repo-name.git

Open the project in your preferred IDE.

Set up the project dependencies by running the following command in the project root directory: mvn clean install

Open the LoginTests class located in the src/test/java/login directory to view the test cases.

Run the test cases by right-clicking on the LoginTests class and selecting "Run As" > "TestNG Test".

Project Structure


The project structure follows the Page Object Model (POM) design pattern, with separate packages 
for pages, tests and resources:

src/main/java: Contains the main Java source code for the project

src/test/java: Contains the test code for the project, including the BaseTests,LoginTests test class and
the user credentials class.

src/test/resources: Contains the testng.xml configuration file for TestNG,screenshots folder and 
the credentials.properties file, where the browser value is specified.
pom.xml: Contains the project dependencies for Maven.

README.md: Contains the documentation and instructions for the project.
Configuration
The testng.xml file contains the TestNG suite configuration for the project, including the test class to be run,
the output directory for the test reports, and the TestNG listeners.







