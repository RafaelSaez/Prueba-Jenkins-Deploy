package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Acciones;

public class PLogin {
	 	
	///////////////// objeto Acciones de paquete utils ///////////
	Acciones accion = new Acciones();
	
	/////////////////// datos dinamicos del flujo ///////////////////
//	private String correo = "mail1t@mail.com";
//	private String contrasena = "Test.1234";
	
	private String correo = "tanner@mail.com";
	private String contrasena = "1234";

	//////////// creacion de variable tipo webdriver ////////////
	WebDriver driver;

	////////////////// elementos de la pagina ///////////////////
	@FindBy(id = "loginEmailInput")
	WebElement txtCorreo;
	@FindBy(id = "loginPasswordInput")
	WebElement txtContrasena;
	@FindBy(className = "MuiButton-label")
	WebElement btnIniciarSesion;
	
	///////////////// elemento de la siguiente página/////////////////
	///////// se utiliza para buscar el resultado esperado////////////
	@FindBy(xpath = "/html/body/div/div/div/div/h1")
	WebElement h1Titulo;
	
	/////////// metodo constructor, inicializa los elementos declarados ////////
	public PLogin(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	////////// flujo completo //////////
	public void Login() {
		accion.setTextEnter(driver, txtCorreo, correo);
		accion.setTextEnter(driver, txtContrasena, contrasena);
		accion.btnClic(driver, btnIniciarSesion);
		accion.assertTextosIguales(driver, h1Titulo, "Búsqueda de Créditoo");
	}
	
	public void LoginEficar() {
		accion.setTextEnter(driver, txtCorreo, correo);
		accion.setTextEnter(driver, txtContrasena, contrasena);
		//accion.btnClic(driver, btnIniciarSesion);
		accion.assertTextosIguales(driver, h1Titulo, "Búsqueda de Crédito");
	}
	
}
