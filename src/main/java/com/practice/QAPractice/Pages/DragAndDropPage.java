package com.practice.QAPractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class DragAndDropPage extends BasePage {
	private static final String URL_BOXES = "https://www.qa-practice.com/elements/dragndrop/boxes";
	private static final String URL_IMAGES = "https://www.qa-practice.com/elements/dragndrop/images";

	private final By dropAbleBox = By.cssSelector("#rect-droppable");
	private final By dragAbleBox = By.cssSelector("#rect-draggable");
	private final By dropAbleText = By.cssSelector("#text-droppable");

	public DragAndDropPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToBoxes() {
		driver.get(URL_BOXES);
	}

	public void navigateToImages() {
		driver.get(URL_IMAGES);
	}

	public boolean isDragAbleVisible() {
		return driver.findElement(dragAbleBox).isDisplayed();
	}

	public boolean isDropAbleVisible() {
		return driver.findElement(dragAbleBox).isDisplayed();
	}
}
