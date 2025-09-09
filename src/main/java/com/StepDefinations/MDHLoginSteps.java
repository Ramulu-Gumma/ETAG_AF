package com.StepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.testng.Assert;

import com.Core.ConfigManager;
import com.Pages.MDHLoginPage;

public class MDHLoginSteps {

	private MDHLoginPage loginPage;

	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
		//ConfigManager.loadConfig();
		String baseUrl = ConfigManager.getBaseUrl();
		loginPage = new MDHLoginPage();
		loginPage.openLoginPage(baseUrl);
		
		// Wait for page to load completely
		try {
			Thread.sleep(3000); // Initial page load wait
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}

	@And("User clicks MDH login button")
	public void user_clicks_mdh_login_button() throws InterruptedException {
		Thread.sleep(8000);
		loginPage.clickLogin();
	}

	@When("User enters valid sgid {string}")
	public void user_enters_valid_sgid(String sgid) {
		loginPage.enterSgid(ConfigManager.getProperty(sgid));
	}

	@Then("User clicks Next button")
	public void user_clicks_next_button() {
		loginPage.clickNext();
	}

	@When("User enters valid password {string}")
	public void user_enters_valid_password(String password) {
		loginPage.enterPassword(ConfigManager.getProperty(password));
	}

	@Then("User clicks Verify button")
	public void user_clicks_verify_button() {
		loginPage.clickVerify();
	}

	@Then("User should able to view the {string} dashboard")
	public void user_should_able_to_view_the_dashboard(String dashboardType) {
		if ("MDH".equalsIgnoreCase(dashboardType)) {
			Assert.assertTrue(loginPage.isMdhDashboardVisible(), 
					"MDH dashboard should be visible");
		} else if ("ETAG".equalsIgnoreCase(dashboardType)) {
			Assert.assertTrue(loginPage.isEtagDashboardVisible(), 
					"ETAG dashboard should be visible");
		}
	}
}