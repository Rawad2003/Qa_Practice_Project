package com.practice.QAPractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class IframePage extends BasePage {
	private static final String IFRAMES_URL = "https://www.qa-practice.com/elements/iframe/iframe_page";

	private final By iframe = By.cssSelector("iframe[class='embed-responsive-item']");
	private final By anyIframe = By.tagName("iframe");
	private final By navbarToggler = By.cssSelector(".navbar-toggler");
	private final By navbarMenu = By.id("navbarHeader");
	private final By aboutParagraph = By.cssSelector("#navbarHeader h4 + p, #navbarHeader p.text-muted");
	private final By twitterLink = By.linkText("Follow on Twitter");
	private final By facebookLink = By.linkText("Like on Facebook");
	private final By emailLink = By.linkText("Email me");
	private final By albumBrand = By.cssSelector("a.navbar-brand");
	private final By mainCallToActionButton = By.linkText("Main call to action");
	private final By secondaryActionButton = By.linkText("Secondary action");
	private final By firstViewButton = By.xpath("(//button[normalize-space()='View'])[1]");
	private final By firstEditButton = By.xpath("(//button[normalize-space()='Edit'])[1]");
	private final By backToTopLink = By.linkText("Back to top");
	private final By visitTheHomePageLink = By.linkText("Visit the homepage");
	private final By gettingStartedGuideLink = By.linkText("getting started guide");
	private final By footer = By.cssSelector("footer");

	public IframePage(WebDriver driver) {
		super(driver);
	}

}
