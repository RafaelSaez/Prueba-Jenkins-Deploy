package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.opencsv.CSVWriter;

public class Acciones {

	/**
	 * Permite realizar un clic a un elemento ingresado por parámetro. El argumento
	 * element debe estar declarado y determinado por @FindBy
	 * <p>
	 * Este metodo imprime por consola el resultado de las validaciones que realiza
	 * cuando busca el elemento. Utiliza isDisplayed e isEnabled. Además, tiene un
	 * WebDriverWait de 10 segundos hasta que el elemento sea visible.
	 * 
	 * @param driver  Webdriver del tipo selenium
	 * @param element Elemento al que se le realizará el clic
	 * @author TestGroup
	 */
	public void btnClic(WebDriver driver, WebElement element) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Método " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			if ((element.isDisplayed() && element.isEnabled())) {
				Thread.sleep(500);
				executor.executeScript("arguments[0].click();", element);
				System.out.println("Exito! Clic al elemento " + element + " exitoso!");
			} else {
				System.out.println("FRACASO! NO se encontró el botón " + element);
			}
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log(msjError);
			//driver.quit();
			driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjError);
		}
	}

	/**
	 * Permite ingresar texto a un elemento HTML de tipo input o textArea Una vez
	 * ingresado el texto presiona la tecla "RETURN". El argumento text debe ser del
	 * tipo String. El argumento element debe estar declarado y determinado
	 * por @FindBy
	 * <p>
	 * Este metodo imprime por consola el resultado de las validaciones que realiza
	 * cuando busca el elemento. Utiliza isDisplayed e isEnabled. Además, tiene un
	 * WebDriverWait de 10 segundos hasta que el elemento sea visible.
	 * 
	 * @param driver  Webdriver del tipo selenium
	 * @param element Elemento al que se le realizará el clic
	 * @param text    Texto a ingresar
	 * @author TestGroup
	 */
	public void setTextEnter(WebDriver driver, WebElement element, String text) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			if ((element.isDisplayed() && element.isEnabled())) {
				element.clear();
				element.sendKeys(text);
				element.sendKeys(Keys.RETURN);
				System.out.println("Exito! Seteo de texto: " + text + " en elemento " + element + " exitoso!");
			} else {
				System.out.println("FRACASO! NO se encontró el inputText " + element);
			}

		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			Assert.fail(msjError);
		}
	}

	/**
	 * Permite ingresar texto a un elemento HTML de tipo input o textArea El
	 * argumento text debe ser del tipo String. El argumento element debe estar
	 * declarado y determinado por @FindBy
	 * <p>
	 * Este metodo imprime por consola el resultado de las validaciones que realiza
	 * cuando busca el elemento. Utiliza isDisplayed e isEnabled. Además, tiene un
	 * WebDriverWait de 10 segundos hasta que el elemento sea visible.
	 * 
	 * @param driver  Webdriver del tipo selenium
	 * @param element Elemento al que se le realizará el clic
	 * @param text    Texto a ingresar
	 * @author TestGroup
	 */
	public void setText(WebDriver driver, WebElement element, String text) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			if ((element.isDisplayed() && element.isEnabled())) {
				element.clear();
				Thread.sleep(100);
				element.sendKeys(text);
				System.out.println("Exito! Seteo de texto: " + text + " en elemento " + element + " exitoso!");
			} else {
				System.out.println("FRACASO! NO se encontró el inputText " + element);
			}

		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			//driver.quit();
			//driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjError);
		}
	}

	/**
	 * Permite realizar una accion del tipo ASSERT EQUALS entre 2 Strings. El
	 * argumento textoEsperado es el String que debe aparecer en la app. El
	 * argumento element debe estar declarado y determinado por @FindBy.
	 * <p>
	 * Este metodo captura el texto del element ingresado por parametro y lo compara
	 * con el texto ingresado por parámetro, realizando un Assert. Además, imprime
	 * por consola los resultados de las validaciones de isDisplayed y WebDriverWait
	 * de 15 segundos. Si el Assert falla, tambien falla el caso de prueba.
	 * 
	 * @param driver        Webdriver del tipo selenium
	 * @param element       Elemento al que se le realizará el clic
	 * @param textoEsperado Texto a comparar
	 * @author TestGroup
	 */
	public void assertTextosIguales(WebDriver driver, WebElement element, String textoEsperado) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		String textoObtenido = "";

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElements(element));
			if ((element.isDisplayed())) {
				textoObtenido = element.getText();
				Assert.assertEquals(textoObtenido.trim(), textoEsperado);
				String msjExito = "ASSERT EXITOSO: Texto obtenido: " + textoObtenido + " IGUAL A Texto Esperado: "
						+ textoEsperado;
				System.out.println(msjExito);
				Reporter.log(msjExito);
			} else {
				System.out.println("FRACASO! NO se encontro el elemento " + element);
			}

		} catch (AssertionError e) {
			String msjFail = "ASSERT FALLIDO: " + e.getMessage();
			System.out.println(msjFail);
			Reporter.log(msjFail);
			Assert.fail(msjFail);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			Assert.fail(msjError);
		}
	}
	
	public void assertTextosIguales_2(WebDriver driver, WebElement element, String textoEsperado) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		String textoObtenido = "";
		textoObtenido = element.getText();
		System.out.println(textoObtenido);
		
		WebElement textoDuro = element;
		System.out.println(textoDuro);
		
		//new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElements(element));
		
		if (element.isDisplayed()) {
			System.out.println("SI");
		} else {
			System.out.println("NO");
		}
		
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElements(element));
		
		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			
				textoObtenido = element.getText();
				Assert.assertEquals(textoObtenido.trim(), textoEsperado);
				String msjExito = "ASSERT EXITOSO: Texto obtenido: " + textoObtenido + " IGUAL A Texto Esperado: "
						+ textoEsperado;
				System.out.println(msjExito);
				Reporter.log(msjExito);
			

		} catch (AssertionError e) {
			String msjFail = "ASSERT FALLIDO: " + e.getMessage();
			System.out.println(msjFail);
			Reporter.log(msjFail);
			Assert.fail(msjFail);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			Assert.fail(msjError);
		}
	}

	/**
	 * Permite realizar una accion del tipo ASSERT NOT EQUALS entre 2 Strings. El
	 * argumento textoEsperado es el String que debe aparecer en la app. El
	 * argumento element debe estar declarado y determinado por @FindBy.
	 * <p>
	 * Este metodo captura el texto del element ingresado por parametro y lo compara
	 * con el texto ingresado por parámetro, realizando un Assert. Además, imprime
	 * por consola los resultados de las validaciones de isDisplayed y WebDriverWait
	 * de 15 segundos. Si el Assert falla, tambien falla el caso de prueba.
	 * 
	 * @param driver        Webdriver del tipo selenium
	 * @param element       Elemento al que se le realizará el clic
	 * @param textoEsperado Texto a comparar
	 * @author TestGroup
	 */
	public void assertTextosDiferentes(WebDriver driver, WebElement element, String textoEsperado) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		String textoObtenido = "";

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			if ((element.isDisplayed())) {
				textoObtenido = element.getText();
				Assert.assertNotEquals(textoObtenido, textoEsperado);
				String msjExito = "ASSERT EXITOSO: Texto obtenido: " + textoObtenido + " DIFERENTE A Texto Esperado: "
						+ textoEsperado;
				System.out.println(msjExito);
				Reporter.log(msjExito);
			} else {
				System.out.println("FRACASO! NO se encontro el elemento " + element);
			}

		} catch (AssertionError e) {
			String msjFail = "ASSERT FALLIDO: " + e.getMessage();
			System.out.println(msjFail);
			Reporter.log(msjFail);
			Assert.fail(msjFail);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			Assert.fail(msjError);
		}
	}

	/**
	 * METODO EXCLUSIVO DE APP ARCHIVO DIGITAL Permite realizar seleccionar la
	 * ultima fila de una tabla. El argumento tabla proviene de un @FindBy de un
	 * elemento tbody. El argumento btnUltimaPagina es el botón ultima página de la
	 * tabla.
	 * <p>
	 * Este metodo calcula la cantidad de filas de la ultima página de la tabla y
	 * realiza un clic sobre el ultimo registro. Además, imprime por consola los
	 * resultados de las validaciones. Si ocurre un error, arroja AssertFail, falla
	 * el caso y detiene la ejecución.
	 * 
	 * @param driver          Webdriver del tipo selenium
	 * @param tabla           Elemento de la app del tipo Tbody
	 * @param btnUltimaPagina Elemento de la tabla que permite ir a la ultima página
	 * @author TestGroup
	 */
	public void clicUltimaFila(WebDriver driver, WebElement tabla, WebElement btnUltimaPagina) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			///////////////// Presionamos botón última página ////////////////////
			btnClic(driver, btnUltimaPagina);
			///////////////// Calcula cantidad de Filas en la Tabla //////////////
			List<WebElement> row = tabla.findElements(By.tagName("tr"));
			int nroFilas = row.size();
			/////////////// Clic en la ultima fila /////////////////////
			WebElement fila;
			if (nroFilas == 1) {
				fila = driver.findElement(By.xpath(
						"/html/body/app-root/div/app-body-accordion/div[2]/mat-accordion/mat-expansion-panel[1]/div/div/app-nuevo/div/div/div[3]/table/tbody/tr/td[1]/mat-checkbox/label/div"));
				System.out.println("Tabla tiene 1 fila");
			} else {
				fila = driver.findElement(By.xpath(
						"/html/body/app-root/div/app-body-accordion/div[2]/mat-accordion/mat-expansion-panel[1]/div/div/app-nuevo/div/div/div[3]/table/tbody/tr["
								+ nroFilas + "]/td[1]/mat-checkbox/label/div"));
				System.out.println("Tabla tiene " + nroFilas + " filas");
			}
			btnClic(driver, fila);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			Assert.fail(msjError);
		}

	}

	public void assertLink(WebDriver driver, WebElement element) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			Assert.assertTrue(element.isDisplayed());
			String msjExito = "ASSERT EXITOSO: Link " + element.getText() + " encontrado.";
			System.out.println(msjExito);
			Reporter.log(msjExito);
		} catch (AssertionError e) {
			String msjFail = "ASSERT FALLIDO: " + e.getMessage();
			System.out.println(msjFail);
			Reporter.log(msjFail);
			Assert.fail(msjFail);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			Assert.fail(msjError);
		}
	}

	public void selectItem(WebDriver driver, WebElement lista, String item) throws InterruptedException {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(lista));
			if ((lista.isDisplayed() && lista.isEnabled())) {
				Select Select1 = new Select(lista);
				Select1.selectByVisibleText(item);
				System.out.println("Exito! Seleccion de " + item + " exitoso!");
			} else {
				System.out.println("NO se encontro " + item + " en la lista.");
			}
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			//driver.quit();
			//driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjError);
		}
	}

	public void selectItem(WebDriver driver, WebElement element, int posicion) throws InterruptedException {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			if ((element.isDisplayed() && element.isEnabled())) {
				element.click();
				Thread.sleep(500);
				for (int i = 0; i < posicion; i++) {
					Thread.sleep(800);
					element.sendKeys(Keys.DOWN);
				}
				element.sendKeys(Keys.ENTER);
				System.out.println("Exito! Seleccion exitosa!");
			} else {
				System.out.println("NO se encontro " + element.getText() + " en la lista.");
			}
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			//driver.quit();
			//driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjError);
		}
	}

	public void assertTrue(WebDriver driver, WebElement element, String texto, int tiempo) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, tiempo).until(ExpectedConditions.visibilityOfAllElements(element));
			new WebDriverWait(driver, tiempo).until(ExpectedConditions.textToBePresentInElement(element, texto));
			Assert.assertTrue(element.isDisplayed());
			String msjExito = "ASSERT EXITOSO: " + element.getText();
			System.out.println(msjExito);
			Reporter.log(msjExito);
		} catch (AssertionError e) {
			String msjFail = "ASSERT FALLIDO: " + e.getMessage();
			System.out.println(msjFail);
			Reporter.log(msjFail);
			driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjFail);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjError);
		}
	}
	
	public void assertTrue_2(WebDriver driver, WebElement element, int tiempo) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, tiempo).until(ExpectedConditions.visibilityOfAllElements(element));
			//new WebDriverWait(driver, tiempo).until(ExpectedConditions.textToBePresentInElement(element, texto));
			Assert.assertTrue(element.isDisplayed());
			String msjExito = "ASSERT EXITOSO: " + element.getText();
			System.out.println(msjExito);
			Reporter.log(msjExito);
		} catch (AssertionError e) {
			String msjFail = "ASSERT FALLIDO: " + e.getMessage();
			System.out.println(msjFail);
			Reporter.log(msjFail);
			driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjFail);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjError);
		}
	}

	public void scrollDown(WebDriver driver, WebElement element) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			System.out.println("Clase " + clase + " | Método " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
			if ((element.isDisplayed() && element.isEnabled())) {
				Thread.sleep(500);
				jse.executeScript("arguments[0].scrollIntoView();", element);
				System.out.println("Exito! elemento visible " + element + " exitoso!");
			} else {
				System.out.println("FRACASO! NO se encontró el elemento " + element);
			}
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log(msjError);
			Assert.fail(msjError);
		}
	}

	public void btnClicJS(WebDriver driver, WebElement element) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Método " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
			if ((element.isDisplayed())) {
				Thread.sleep(500);
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				System.out.println("Exito! Clic al elemento " + element + " exitoso!");
			} else {
				System.out.println("FRACASO! NO se encontró el botón " + element);
			}
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log(msjError);
			Assert.fail(msjError);
		}
	}
	public void assertCredito(WebDriver driver, WebElement element) {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			Assert.assertTrue(element.isDisplayed());
			String msjExito = "ASSERT EXITOSO: Id Credito " + element.getText() + " encontrado.";
			System.out.println(msjExito);
			Reporter.log(msjExito);
		} catch (AssertionError e) {
			String msjFail = "ASSERT FALLIDO: " + e.getMessage();
			System.out.println(msjFail);
			Reporter.log(msjFail);
			Assert.fail(msjFail);
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			Assert.fail(msjError);
		}
	}
	
	public void selectTAB(WebDriver driver, WebElement element, int posicion) throws InterruptedException {

		String metodo = Thread.currentThread().getStackTrace()[2].getMethodName();
		String clase = Thread.currentThread().getStackTrace()[2].getClassName();

		try {
			System.out.println("Clase " + clase + " | Metodo " + metodo);
			Actions act = new Actions(driver);
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(element));
			if ((element.isDisplayed() && element.isEnabled())) {
				element.click();
				for (int i = 0; i < posicion; i++) { //position 28
					
					Thread.sleep(200);
					act.sendKeys(Keys.TAB).build().perform();
					
					}
				act.sendKeys(Keys.ENTER).build().perform();
				
				System.out.println("Exito! Seleccion exitosa!");
			} else {
				System.out.println("NO se encontro " + element.getText() + " en la lista.");
			}
		} catch (Exception e) {
			String msjError = "Error de Sistema: " + e.getMessage();
			System.out.println(msjError);
			Reporter.log("<br>" + msjError + "</br>");
			//driver.quit();
			//driver.close(); //Cierra la ventanap, para que termine el flujo
			Assert.fail(msjError);
		}
	}
	
	public void escribirCsv(String idCredito) throws IOException {
		String curDir = System.getProperty("user.dir");
		CSVWriter writecsv = new CSVWriter(new FileWriter(curDir+"\\Id_CreditosSalesForce.csv", true));
		String[] credito = { idCredito.replaceAll("[^\\d.]", "") };
		writecsv.writeNext(credito);
//		String[] credito2 = { txtxRut.replaceAll("[^\\d.]", "") };
//		writecsv.writeNext(credito2);
		writecsv.close();
	}

}