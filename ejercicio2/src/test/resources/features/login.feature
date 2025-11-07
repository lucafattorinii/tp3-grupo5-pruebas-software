# language: es
@saucedemo
Caracteristica: Inicio de sesion en SauceDemo
  Como usuario de SauceDemo
  Quiero poder iniciar sesion en la aplicacion
  Para acceder a los productos disponibles

  Escenario: Inicio de sesion exitoso con credenciales validas
    Dado que estoy en la pagina de inicio de SauceDemo
    Cuando ingreso el nombre de usuario "standard_user" y la contraseña "secret_sauce"
    Y hago clic en el boton de inicio de sesion
    Entonces debo ser redirigido a la pagina de productos
    Y debo ver el titulo "PRODUCTS"

  Escenario: Inicio de sesion fallido con credenciales invalidas
    Dado que estoy en la pagina de inicio de SauceDemo
    Cuando ingreso el nombre de usuario "usuario_invalido" y la contraseña "contraseña_invalida"
    Y hago clic en el boton de inicio de sesion
    Entonces debo ver un mensaje de error que diga "Epic sadface: Username and password do not match any user in this service"
