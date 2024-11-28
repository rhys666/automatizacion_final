Feature: Login y Registro

  Scenario: Inicio de sesión con datos válidos
    Given El usuario está en la página de inicio de sesión
    When El usuario ingresa un nombre de usuario y una contraseña válidos
    Then El usuario debe ser redirigido al panel principal

  Scenario: Inicio de sesión con datos incorrectos
    Given El usuario está en la página de inicio de sesión
    When El usuario ingresa un nombre de usuario y una contraseña incorrectos
    Then El usuario debe ver un mensaje de error de "Credenciales incorrectas"
