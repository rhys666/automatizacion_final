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

  
/*
   
    @When("El ejecutivo ingresa un nombre de usuario y una contraseña válidos")
    public void el_ejecutivo_ingresa_un_nombre_de_usuario_y_una_contraseña_válidos() throws InterruptedException {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));
        
        username.sendKeys("andres"); // Ingresar nombre de usuario válido para el ejecutivo
        password.sendKeys("123456"); // Ingresar contraseña válida para el ejecutivo
        loginButton.click();
        Thread.sleep(5000);
    }

    @Then("El ejecutivo debe ser redirigido al menú principal del ejecutivo")
    public void el_ejecutivo_debe_ser_redirigido_al_menu_principal_del_ejecutivo() throws InterruptedException {
        // Verificar si la redirección es exitosa, por ejemplo:
        assertTrue(driver.getCurrentUrl().contains("/menuEjecutivo")); // Cambia esta URL según tu implementación
        Thread.sleep(5000);
    }

    @When("El ejecutivo crea un nuevo cliente ingresando los datos del cliente")
    public void el_ejecutivo_crea_un_nuevo_cliente_ingresando_los_datos_del_cliente() throws InterruptedException {
        WebElement clienteRut = driver.findElement(By.id("clienteRut"));
        WebElement clienteNombre = driver.findElement(By.id("clienteNombre"));
        WebElement clienteApellido = driver.findElement(By.id("clienteApellido"));
        WebElement clienteDireccion = driver.findElement(By.id("clienteDireccion"));
        WebElement clienteCorreo = driver.findElement(By.id("clienteCorreo"));
        WebElement clienteTelefono = driver.findElement(By.id("clienteTelefono"));
        WebElement clienteMascota = driver.findElement(By.id("clienteMascota"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));
        
        // Ingresar datos del cliente
        clienteRut.sendKeys("123456789"); // Rut del cliente
        clienteNombre.sendKeys("Juan"); // Nombre del cliente
        clienteApellido.sendKeys("Pérez"); // Apellido del cliente
        clienteDireccion.sendKeys("Domeyko 123"); // Dirección del cliente
        clienteCorreo.sendKeys("juan.perez@gmail.com"); // Correo electrónico del cliente
        clienteTelefono.sendKeys("987654321"); // Teléfono del cliente
        clienteMascota.sendKeys("Pikachu"); // Nombre de la mascota del cliente
        Thread.sleep(5000);
        // Enviar el formulario para crear el cliente
        submitButton.click();
        Thread.sleep(5000);
    }

    @Then("El ejecutivo debe ver un mensaje de éxito con el ID del nuevo cliente creado")
    public void el_ejecutivo_debe_ver_un_mensaje_de_éxito_con_el_id_del_nuevo_cliente_creado() throws InterruptedException {
        WebElement successMessage = driver.findElement(By.id("successMessage"));
        assertTrue(successMessage.getText().contains("Cliente creado con éxito"));
        Thread.sleep(5000);
    }
   */
       

    // Después de los tests, cerramos el driver
    @After
    public void cerrarDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}