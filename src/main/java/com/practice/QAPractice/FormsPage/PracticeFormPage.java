package com.practice.QAPractice.FormsPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practice.QAPractice.BasePage.BasePage;

public class PracticeFormPage extends BasePage {

	private static final String URL = "https://www.qa-practice.com/forms/practice-form";

	// inputs:
	private final By firstNameInput = By.name("first_name");
	private final By lastNameInput = By.name("last_name");
	private final By emailInput = By.name("email");
	private final By mobileInput = By.name("mobile");
	private final By dateOfBirthInput = By.name("date_of_birth");
	private final By subjectsInput = By.id("subjectsAutocomplete");
	private final By currentAddressInput = By.name("current_address");

	// Select Inputs:
	private final By stateControl = By.cssSelector("#div_id_state .custom-dropdown-control");
	private final By cityControl = By.cssSelector("#div_id_city .custom-dropdown-control");

	// Radio Buttons:
	private final By maleRadioButton = By.id("gender_0");
	private final By femaleRadioButton = By.id("gender_1");
	private final By otherRadioButton = By.id("gender_2");

	// CheckBoxes:
	private final By sportsCheckbox = By.id("hobbies_0");
	private final By readingCheckbox = By.id("hobbies_1");
	private final By musicCheckbox = By.id("hobbies_2");

	// result Elements:
	private final By resultModal = By.id("resultsModal");
	private final By resultModalTitle = By.id("resultsModalLabel");
	private final By closeResultModalButton = By.id("closeLargeModal");

	// Submit button:
	private final By submitButton = By.id("submit-id-submit");

	public PracticeFormPage(WebDriver driver) {
		super(driver);
	}

	public void navigateTo() {
		driver.get(URL);
	}

	// Entering inputs methods:
	public void enterFirstName(String value) {
		driver.findElement(firstNameInput).sendKeys(value);
	}

	public void enterLastName(String value) {
		driver.findElement(lastNameInput).sendKeys(value);
	}

	public void enterEmail(String value) {
		driver.findElement(emailInput).sendKeys(value);
	}

	public void selectGender(String gender) throws Exception {
		switch (gender) {
		case "Male":
			scrollToAndClick(maleRadioButton);
			break;
		case "Female":
			scrollToAndClick(femaleRadioButton);
			break;
		case "Other":
			scrollToAndClick(otherRadioButton);
			break;
		default:
			throw new Exception("Invalid gender: " + gender);
		}
	}

	public void enterMobile(String value) {
		driver.findElement(mobileInput).sendKeys(value);
	}

	public void enterDateOfBirth(String value) {
		WebElement dob = driver.findElement(dateOfBirthInput);
		((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", dob, value);
	}

	public void enterSubject(String subject) {
		WebElement input = driver.findElement(subjectsInput);
		input.sendKeys(subject);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By suggestion = By.xpath("//*[contains(@class,'autocomplete-suggestion') or contains(@class,'suggestion')]"
				+ "[contains(.,'" + subject + "')]");
		try {
			WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
			option.click();
		} catch (Exception e) {
			input.sendKeys(Keys.ENTER);
		}
	}

	public void selectHobby(String hobby) throws Exception {
		switch (hobby) {
		case "Male":
			scrollToAndClick(sportsCheckbox);
			break;
		case "Female":
			scrollToAndClick(readingCheckbox);
			break;
		case "Other":
			scrollToAndClick(musicCheckbox);
			break;
		default:
			throw new Exception("Invalid gender: " + hobby);
		}
	}

	public void enterCurrentAddress(String value) {
		driver.findElement(currentAddressInput).sendKeys(value);
	}

	public void selectState(String state) {
		scrollToAndClick(stateControl);
		By option = By.cssSelector("#div_id_state .custom-dropdown-option[data-value='" + state + "']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	}

	public void selectCity(String city) {
		scrollToAndClick(stateControl);
		By option = By.cssSelector("#div_id_city .custom-dropdown-option[data-value='" + city + "']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	}

	// Scroll and click element method(shared):
	public void scrollToAndClick(By locator) {
		WebElement element = driver.findElement(locator);
		new Actions(driver).scrollToElement(element).perform();
		element.click();
	}
}
