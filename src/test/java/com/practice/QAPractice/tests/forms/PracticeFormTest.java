package com.practice.QAPractice.tests.forms;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.FormsPage.PracticeFormPage;

public class PracticeFormTest extends BaseTest {

	@DataProvider(name = "practiceFormData")
	public Object[][] practiceFormData() {
		// Columns: firstName, lastName, email, gender, mobile, dob, subjects, hobby, state, city,
		// address, expected, description
		return new Object[][] {
				{ "John", "Doe", "john@test.com", "Male", "1234567890", "", "", "", "", "", "", true,
						"Valid data: all required fields filled correctly" },
				{ "John", "Doe", "john@test.com", "Male", "1234567890", "", "", "Sports,Reading", "", "", "", true,
						"Valid data: optional hobbies selected (Sports and Reading)" },
				{ "John", "Doe", "john@test.com", "Male", "1234567890", "", "", "", "NCR", "Delhi", "", true,
						"Valid data: state and city selected" },
				{ "John", "Doe", "john@test.com", "Male", "1234567890", "", "", "", "", "", "123 Test Street, Test City",
						true, "Valid data: current address filled" },
				{ "John", "Doe", "john@test.com", "Male", "1234567890", "05 Jun 2026", "Maths",
						"Sports,Reading,Music", "NCR", "Delhi", "123 Test Street, Test City", true,
						"Valid data: all fields filled except picture" },
				{ "", "Doe", "john@test.com", "Male", "1234567890", "", "", "", "", "", "", false,
						"Invalid data: first name empty" },
				{ "John", "", "john@test.com", "Male", "1234567890", "", "", "", "", "", "", false,
						"Invalid data: last name empty" },
				{ "John", "Doe", "john@test.com", "", "1234567890", "", "", "", "", "", "", false,
						"Invalid data: no gender selected" },
				{ "John", "Doe", "john@test.com", "Male", "", "", "", "", "", "", "", false,
						"Invalid data: mobile empty" },
				{ "John", "Doe", "john@test.com", "Male", "123", "", "", "", "", "", "", false,
						"Invalid data: mobile less than 10 digits" },
				{ "John", "Doe", "john@test.com", "Male", "12345678901", "", "", "", "", "", "", true,
						"Valid data: mobile >10 digits truncated to 10 by maxlength" },
				{ "John", "Doe", "notanemail", "Male", "1234567890", "", "", "", "", "", "", false,
						"Invalid data: wrong email format" }, };
	}

	@Test(dataProvider = "practiceFormData", priority = 51)
	public void test_11_1_PracticeForm(String firstName, String lastName, String email, String gender, String mobile,
			String dob, String subjects, String hobby, String state, String city, String address, boolean expected,
			String description) throws Exception {
		PracticeFormPage page = new PracticeFormPage(driver);
		page.navigateTo();
		waitForPageReady();
		if (!firstName.isEmpty()) {
			page.enterFirstName(firstName);
			waitForPageReady();
		}
		if (!lastName.isEmpty()) {
			page.enterLastName(lastName);
			waitForPageReady();
		}
		if (!email.isEmpty()) {
			page.enterEmail(email);
			waitForPageReady();
		}
		if (!gender.isEmpty()) {
			page.selectGender(gender);
			waitForPageReady();
		}
		if (!mobile.isEmpty()) {
			page.enterMobile(mobile);
			waitForPageReady();
		}
		if (!dob.isEmpty()) {
			page.enterDateOfBirth(dob);
			waitForPageReady();
		}
		if (!subjects.isEmpty()) {
			page.enterSubject(subjects);
			waitForPageReady();
		}
		if (!hobby.isEmpty()) {
			for (String h : hobby.split(",")) {
				page.selectHobby(h.trim());
				waitForPageReady();
			}
		}
		if (!state.isEmpty()) {
			page.selectState(state);
			waitForPageReady();
		}
		if (!city.isEmpty()) {
			page.selectCity(city);
			waitForPageReady();
		}
		if (!address.isEmpty()) {
			page.enterCurrentAddress(address);
			waitForPageReady();
		}
		page.submitForm();
		waitForPageReady();
		if (expected) {
			Assert.assertTrue(page.isResultModalVisible(), description);
			Assert.assertEquals(page.getResultModalTitle(), "Thanks for submitting the form", description);
			page.closeResultModal();
			Assert.assertTrue(page.isResultModalGone(), description);
		} else {
			Assert.assertFalse(page.isResultModalVisible(), description);
		}
	}
}
