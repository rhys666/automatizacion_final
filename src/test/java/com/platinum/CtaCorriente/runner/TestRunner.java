package com.platinum.CtaCorriente.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/login.feature", // Ruta al archivo .feature
    glue = "com.platinum.CtaCorriente.steps", // Ruta a las clases de steps
    plugin = {"pretty", "html:target/cucumber-report.html"} // Reportes HTML
)
public class TestRunner {
}