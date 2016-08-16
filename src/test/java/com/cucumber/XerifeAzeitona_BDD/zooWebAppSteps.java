package com.cucumber.XerifeAzeitona_BDD;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class zooWebAppSteps {

	WebDriver driver;
	WebElement element;	

	@Given("^I am on the zoo site$")
	public void shouldNavigateToZooSite() throws Throwable {
		driver = new FirefoxDriver();
		driver.navigate().to("http://www.thetestroom.com/webapp/index.html");
	}

	@When("^I navigate to the contact page$")
	public void shouldClickOnContactLink() throws Throwable {
		driver.findElement(By.id("contact_link")).click();		
	}

	@And("^Fill the form with valid data$")
	public void shouldPopulateContactForm() throws Throwable {
		driver.findElement(By.name("name_field")).sendKeys("Xerife Azeitona");
		driver.findElement(By.id("radop")).click();
		driver.findElement(By.id("cadop")).click();
		driver.findElement(By.name("address_field")).sendKeys("555, Oliveiras St. N");
		driver.findElement(By.name("postcode_field")).sendKeys("J3N 4G9");
		driver.findElement(By.name("email_field")).sendKeys("xerifeazeitona@oliveiras.com");
		driver.findElement(By.id("submit_message")).click();
	}

	@Then("^I check that the form has been submitted$")
	public void checkPageTitleForContactSubmission() throws Throwable {
		Assert.assertTrue("Not on confirmation page", driver.getTitle().equals("Contact Confirmation"));
	}

	@When("^I navigate to the adoption page$")
	public void shouldClickOnAdoptionLink() throws Throwable {
		driver.findElement(By.id("adoption_link")).click();		
	}

	@When("^Select the start date for adoption$")
	public void shouldSelectDate() throws Throwable {
		driver.findElement(By.id("start_select")).click();
		driver.findElement(By.id("start_select")).sendKeys("First day of the next week");
	}

	@When("^Check if a turtle is available$")
	public void shouldClickOnTurtleCheckButton() throws Throwable {
		driver.findElement(By.id("check_btn_02")).click();		
	}

	@When("^I fill the form with the following data$")
	public void shouldPopulateContactForm(DataTable table) throws Throwable {
		//populates a list with the data from table
		List<List<String>> data = table.raw();
		
		driver.findElement(By.name(data.get(1).get(1))).sendKeys(data.get(1).get(2));
		driver.findElement(By.name(data.get(2).get(1))).sendKeys(data.get(2).get(2));
		driver.findElement(By.name(data.get(3).get(1))).sendKeys(data.get(3).get(2));
		driver.findElement(By.name(data.get(4).get(1))).sendKeys(data.get(4).get(2));
		driver.findElement(By.id("submit_adoption")).click();
	}

	@Then("^I check that the adoption has been set up$")
	public void checkPageTitleForAdoption() throws Throwable {
		Assert.assertTrue("Not on adoption set up page", driver.getTitle().equals("Adoption Result | Confirmed"));
	}
	
	@Given("^I am on Google$")
	public void shouldNavigateToGoogle() throws Throwable {
		/* Jenkins Security

		 * Maven Integration Plugin with HTML Reports enabled: 
		   System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "default-src 'none'; script-src 'self' img-src 'self'; style-src 'self'")

		 * Set a custom value for the header:
		   System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "sandbox; default-src 'self';")
		   
		 * Unset the header:
		   System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")
		   
		 * Set the header to the default:
		   System.clearProperty("hudson.model.DirectoryBrowserSupport.CSP")
		   
		 * Find out the current header value:
		   System.getProperty("hudson.model.DirectoryBrowserSupport.CSP")
		*/
		
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("no-sandbox");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);		
		driver = new ChromeDriver(capabilities);
		driver.get("http://www.google.com");
	}
	
	@When("^I perform a search$")
	public void shouldPerformSearch() throws Throwable {
	        // Find the text input element by its name
	        element = driver.findElement(By.name("q"));
	        // Enter something to search for
	        element.sendKeys("BDD Gherkin");
	        // Now submit the form. WebDriver will find the form for us from the element
	        element.submit();
	}

	@Then("^I check that the search was made$")
	public void checkPageTitleForSearchSubmission() throws Throwable {
	    // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("bdd gherkin");
            }
        });
      //Close the browser
        driver.quit();
	}
}
