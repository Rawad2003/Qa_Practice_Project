package com.practice.QAPractice.Pages;

import org.openqa.selenium.By;

public class PopUpPage {
	private static final String URL_MODAL = "https://www.qa-practice.com/elements/popup/modal";
	private static final String IFRAME_POPUP = "https://www.qa-practice.com/elements/popup/iframe_popup";

	private final By launchButton = By.cssSelector("button[data-bs-target='#exampleModal']");
	private final By modal = By.id("exampleModal");
	private final By modalShown = By.cssSelector(".modal.show");
	private final By modalTitle = By.id("exampleModalLabel");
	private final By checkBox = By.id("id_checkbox_0");
	private final By closeButton = By.cssSelector(".modal-footer .btn-secondary");
	private final By sendButton = By.cssSelector(".modal-footer .btn-primary");
	private final By resultSection = By.xpath("//p[contains(text(),'Selected checkboxes')]");
	private final By textCopy = By.id("text-to-copy");
	private final By pasteTextInput = By.xpath("//input[contains(@class,'textinput')]");
	private final By popupIframe = By.cssSelector("#exampleModal iframe");
	private final By correctResult = By.xpath("//*[contains(@role,'alert') and contains(text(),'Correct')]");
	private final By nopeResult = By.xpath("//*[contains(@role,'alert') and contains(text(),'Nope')]");
}
