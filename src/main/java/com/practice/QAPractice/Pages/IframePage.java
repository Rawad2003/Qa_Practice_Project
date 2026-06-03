package com.practice.QAPractice.Pages;

import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class IframePage extends BasePage {
	private static final String IFRAMES_URL = "https://www.qa-practice.com/elements/iframe/iframe_page";

	public IframePage(WebDriver driver) {
		super(driver);
	}

}
