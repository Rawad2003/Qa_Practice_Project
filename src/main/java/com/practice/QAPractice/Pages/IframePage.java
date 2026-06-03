package com.practice.QAPractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class IframePage extends BasePage {
	private static final String IFRAMES_URL = "https://www.qa-practice.com/elements/iframe/iframe_page";

	private final By iframe = By.cssSelector("iframe[class='embed-responsive-item']");
	private final By anyIframe = By.tagName("iframe");

	public IframePage(WebDriver driver) {
		super(driver);
	}

}
