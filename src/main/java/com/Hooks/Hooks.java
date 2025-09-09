package com.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.Drivers.DriverManager;

public class Hooks {

	@Before
	public void setUp(Scenario scenario) {

		DriverManager.createDriver();
		System.out.println("Starting scenario: " + scenario.getName());

	}

	@After
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {
			// Capture screenshot on failure
			captureScreenshot(scenario);
		}
		DriverManager.quitDriver();
		System.out.println("Completed scenario: " + scenario.getName() + " - Status: " + scenario.getStatus());
	}

	private void captureScreenshot(Scenario scenario) {
		try {
			if (DriverManager.getDriver() != null) {
				// Take screenshot
				byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);

				// Attach to Cucumber report
				scenario.attach(screenshot, "image/png", scenario.getName() + "_failure.png");

				System.out.println("Screenshot captured for failed scenario: " + scenario.getName());
			}
		} catch (Exception e) {
			System.out.println("Failed to capture screenshot: " + e.getMessage());
		}
	}

}
