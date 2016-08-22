/**
 * 
 */
package com.cucumber.XerifeAzeitona_BDD;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Fernanda
 *
 */
public class EasySelenium {

	// enum type to select target browser
	public enum TargetBrowser {
		FIREFOX, CHROME, INTERNET_EXPLORER
	}

	// Selenium WebDriver
	WebDriver seleniumWebDriver;
	// Browser to be used for running the tests (default: Firefox)
	TargetBrowser targetBrowser;
	// Path to webdriver, only necessary for Google Chrome and Internet Explorer
	// (default: project folder)
	String webDriverLocation;
	// URL that should be navigated to (default: DuckDuckGo search page)
	String targetURL;
	// UI Element currently being used
	WebElement element;

	/**
	 * Set default values for properties
	 */
	public EasySelenium() {
		targetBrowser = TargetBrowser.FIREFOX;
		webDriverLocation = "";
		targetURL = "https://duckduckgo.com/html";
	}

	/**
	 * Open Browser and navigate to target URL
	 */
	public void OpenBrowser() {
		InitializeTargetBrowser();
	}
	public void OpenBrowser(String url) {
		targetURL = url;
		InitializeTargetBrowser();
	}

	public void OpenBrowser(String url, TargetBrowser browser) {
		targetURL = url;
		targetBrowser = browser;
		InitializeTargetBrowser();
	}

	public void OpenBrowser(String url, TargetBrowser browser, String driverLocation) {
		targetURL = url;
		targetBrowser = browser;
		webDriverLocation = driverLocation;
		InitializeTargetBrowser();
	}

	public void OpenBrowser(TargetBrowser browser) {
		targetBrowser = browser;
		InitializeTargetBrowser();
	}

	public void OpenBrowser(TargetBrowser browser, String driverLocation) {
		targetBrowser = browser;
		webDriverLocation = driverLocation;
		InitializeTargetBrowser();
	}

	/**
	 * Start Selenium WebDriver and navigate to default page
	 */
	private void InitializeTargetBrowser() {
		switch (targetBrowser) {
		case CHROME:
			if (webDriverLocation == "") {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			} else {
				System.setProperty("webdriver.chrome.driver", webDriverLocation + "\\chromedriver.exe");
			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("no-sandbox");
			options.addArguments("start-maximized");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			seleniumWebDriver = new ChromeDriver(capabilities);
			break;

		case INTERNET_EXPLORER:
			seleniumWebDriver = new InternetExplorerDriver();
			seleniumWebDriver.manage().window().maximize();
			break;

		case FIREFOX:
			seleniumWebDriver = new FirefoxDriver();
			seleniumWebDriver.manage().window().maximize();
			break;
		}
		seleniumWebDriver.get(targetURL);
	}

	/**
	 * Fill Input Text located in xPath with provided text
	 */
	public void InputText(String xPath, String text) {
		element = seleniumWebDriver.findElement(By.xpath(xPath));
		element.clear();
		element.sendKeys(text);
	}

	public void InputTextByName(String name, String text) {
		element = seleniumWebDriver.findElement(By.name(name));
		element.clear();
		element.sendKeys(text);
	}

	public void InputTextById(String id, String text) {
		element = seleniumWebDriver.findElement(By.id(id));
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * Try to click on the provided element
	 */
	public void Click(String xPath) {
		element = seleniumWebDriver.findElement(By.xpath(xPath));
		element.click();
	}
	
	public void ClickByName(String name) {
		element = seleniumWebDriver.findElement(By.name(name));
		element.click();
	}

	public void ClickById(String id) {
		element = seleniumWebDriver.findElement(By.id(id));
		element.click();
	}

	/**
	 * Return the Text property of the provided element 
	 */
	public String getText(String xPath) {
		element = seleniumWebDriver.findElement(By.xpath(xPath));
		return element.getText();
	}
	
	public String getTextByName(String name) {
		element = seleniumWebDriver.findElement(By.name(name));
		return element.getText();
	}

	public String getTextById(String id) {
		element = seleniumWebDriver.findElement(By.id(id));
		return element.getText();
	}

	/**
	 * Try to select the provided option in a select 
	 */
	public void SelectOption(String xPath, String optionToSelect) {
		element = seleniumWebDriver.findElement(By.xpath(xPath));
		element.sendKeys(optionToSelect);
	}

	public void SelectOptionByName(String name, String text) {
		element = seleniumWebDriver.findElement(By.name(name));
		element.sendKeys(text);
	}

	public void SelectOptionById(String id, String text) {
		element = seleniumWebDriver.findElement(By.id(id));
		element.sendKeys(text);
	}

	/**
	 * Try to submit from the selected element. WebDriver will find the form for
	 * us from the element
	 */
	public void Submit() {
		element.submit();
	}

	/**
	 * Tries submit from the selected element. WebDriver will find the form for
	 * us from the element
	 */
	public void VerifyPageTitle(String expectedTitle) {
		final String title = expectedTitle;
		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(seleniumWebDriver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().contains(title.toLowerCase());
			}
		});
	}

	/**
	 * Wait until provided element is displayed
	 * Timeout is 30 seconds
	 */
	public void WaitUntilElementIsVisible(String xPath) {
		final String xPathRef = xPath;
		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(seleniumWebDriver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.xpath(xPathRef)).isDisplayed();
			}
		});
	}

	public void WaitUntilElementIsVisibleByName(String name) {
		final String nameRef = name;
		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(seleniumWebDriver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.name(nameRef)).isDisplayed();
			}
		});
	}

	public void WaitUntilElementIsVisibleById(String id) {
		final String idRef = id;
		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(seleniumWebDriver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.id(idRef)).isDisplayed();
			}
		});
	}

	/**
	 * Close the browser
	 */
	public void CloseBrowser() {
		seleniumWebDriver.close();
	}

}
