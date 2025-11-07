package com.saucedemo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "com.saucedemo.stepdefinitions",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
        "json:target/cucumber-reports/CucumberTestReport.json",
        "junit:target/cucumber-reports/CucumberTestReport.xml"
    },
    monochrome = true,
    tags = "@web and (@successful-login or @failed-login)"
)
public class CucumberTestSuite {
    // Clase vacía utilizada solo como punto de entrada para JUnit
}
