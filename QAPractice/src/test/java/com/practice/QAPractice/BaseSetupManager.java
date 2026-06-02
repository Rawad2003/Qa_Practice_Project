package com.practice.QAPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.Scanner;

public class BaseSetupManager {

	protected static WebDriver driver;
	protected static String baseUrL;

	@BeforeSuite
	public void initDriver() throws Exception {

		// ── Step 1: Ask user to pick a browser ───────────────────────────────
		System.out.println("========================================");
		System.out.println("  Select Browser to Run Tests:");
		System.out.println("  1 - Chrome");
		System.out.println("  2 - Edge");
		System.out.println("  3 - Firefox");
		System.out.println("========================================");
		System.out.print("Enter your choice (1/2/3): ");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		sc.nextLine(); // clear buffer

		// ── Step 2: Ask user for the URL to test ─────────────────────────────
		System.out.println("========================================");
		System.out.print("Enter the URL to test (e.g. https://example.com): ");
		baseUrL = sc.nextLine().trim();

		// ── Step 3: Setup driver automatically using WebDriverManager ─────────
		switch (choice) {
		case 1:
			System.out.println("[Launcher] Setting up Chrome driver...");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("[Launcher] Chrome started successfully.");
			break;

		case 2:
			System.out.println("[Launcher] Setting up Edge driver...");
//			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("[Launcher] Edge started successfully.");
			break;

		case 3:
			System.out.println("[Launcher] Setting up Firefox driver...");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("[Launcher] Firefox started successfully.");
			break;

		default:
			throw new IllegalArgumentException("Invalid choice: " + choice);
		}

		// ── Step 4: Open the URL ──────────────────────────────────────────────
		driver.manage().window().maximize();
		driver.get(baseUrL);
		System.out.println("[Launcher] Navigated to: " + baseUrL);
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("[Teardown] Browser closed.");
		}
	}
}
