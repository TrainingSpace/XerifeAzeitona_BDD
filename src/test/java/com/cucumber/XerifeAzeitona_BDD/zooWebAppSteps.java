package com.cucumber.XerifeAzeitona_BDD;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class zooWebAppSteps {

	WebDriver driver;

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
}
