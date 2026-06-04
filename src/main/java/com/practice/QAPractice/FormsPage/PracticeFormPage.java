package com.practice.QAPractice.FormsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
	private final By ReadingCheckbox = By.id("hobbies_1");
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

}
