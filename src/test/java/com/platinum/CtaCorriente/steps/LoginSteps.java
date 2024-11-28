package com.platinum.CtaCorriente.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.time.Duration;


public class LoginSteps {
	
	 WebDriver driver;
    @Given("El usuario esta en la pagina de inicio de sesion")
    public void el_usuario_está_en_la_página_de_inicio_de_sesión() throws InterruptedException {
        // Configurar la ubicación del FirefoxDriver
        System.setProperty("webdriver.gecko.driver", "D:/Java Projects/Automatizacion_Final/chrome-win64/geckodriver.exe");

        driver = new FirefoxDriver();
        // Abrir la página de login
        driver.get("http://localhost:8080/CtaCorriente/login"); // URL de página de login
        Thread.sleep(5000);
    }

    @When("El usuario ingresa un nombre de usuario y una contrasena validos")
    public void el_usuario_ingresa_un_nombre_de_usuario_y_una_contraseña_válidos() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        
        username.sendKeys("michel");
        password.sendKeys("123456");
        loginButton.click();
    }

    @Then("El usuario debe ser redirigido al panel principal")
    public void el_usuario_debe_ser_redirigido_al_panel_principal() {
        // Verificar si la redirección es exitosa, por ejemplo:
        assertTrue(driver.getCurrentUrl().contains("/menuUsuario"));
    }

    @When("El usuario ingresa un nombre de usuario y una contrasena incorrectos")
    public void el_usuario_ingresa_un_nombre_de_usuario_y_una_contraseña_incorrectos() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        username.sendKeys("michel");
        password.sendKeys("654321");
        loginButton.click();
    }

    @Then("El usuario debe ver un mensaje de error de Credenciales incorrectas")
    public void el_usuario_debe_ver_un_mensaje_de_error() {
        
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));
    	    
    	    // Verificar que el mensaje de error contiene el texto esperado
    	    assertTrue(errorMessage.getText().contains("Credenciales invalidas"));
    }

  
    // Después de los tests, cerramos el driver
    @After
    public void cerrarDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}