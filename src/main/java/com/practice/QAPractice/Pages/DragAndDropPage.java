package com.practice.QAPractice.Pages;

import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class DragAndDropPage extends BasePage {
	private static final String URL_BOXES = "https://www.qa-practice.com/elements/dragndrop/boxes";
	private static final String URL_IMAGES = "https://www.qa-practice.com/elements/dragndrop/images";

	public DragAndDropPage(WebDriver driver) {
		super(driver);
	}

}
