package scripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.PLogin;

public class Tests {
	
///// variables para poder seleccionar en la aplicacion el navegador /////
	private WebDriver driver;

	///// variable de la pagina a ocupar que estan en el esqueleto /////
	public static String curDir = System.getProperty("user.dir");
	PLogin objLogin;
	
	public void versionChrome() throws IOException, InterruptedException {

		String command = "google-chrome --version";
		Process proc = Runtime.getRuntime().exec(command);
		// Read the output
		BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			System.out.print(line + "\n");
		}

		proc.waitFor();
	}

	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void beforeMethod() {

		try {
			///////////////// Asignacion de variables dinámicas de configuracion
			///////////////// ////////////////

			String baseUrl = "https://amices.testing.amicar.com/login";
			String navegador = "chrome";
			//String driverPath = "/usr/bin/chromedriver";
			String driverPath = "C:\\chromedriver.exe";
			String TipoWebDriver = "webdriver.chrome.driver";

			if (navegador.equals("chrome")) {
				System.setProperty(TipoWebDriver, driverPath);
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--no-sandbox");
				//chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--window-size=1920,1200");
				chromeOptions.addArguments("--ignore-certificate-errors", "--disable-extensions");
				driver = new ChromeDriver(chromeOptions);

			} else if (navegador.equals("explorer")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				System.setProperty(TipoWebDriver, driverPath);
				driver = new InternetExplorerDriver(capabilities);

			} else {
				System.out.println("Error: el navegador " + navegador + " NO es compatible.");
			}

			///// Maximiza ventana del explorador y luego redirecciona a baseURL /////
			driver.manage().window().maximize();
			driver.get(baseUrl);

			///// Alerta Mensaje solo Explorer /////
			if (navegador.equals("explorer")) {
				try {
					Alert alerta = driver.switchTo().alert();
					alerta.accept();
				} catch (Exception ex) {
					System.out.println("Error de Navegador explorer " + ex.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error en la configuracion inicial: " + e.getMessage());
		}

	}
	
  @Test
  public void Prueba() {
	  
	  objLogin = new PLogin(driver);
	  objLogin.Login();
	  
	  driver.close();
	  driver.quit();
  }
  
}
    