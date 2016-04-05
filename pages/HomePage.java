package pages;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.dashboard.BaseDashboard;

public class HomePage extends AbstractComponent {
	protected WebDriver		wd;
	protected WebDriverWait	w;
	protected BaseDashboard	dash;
	ArrayList<String>		browserWindows;

	/* --- Element locators --- START */

	private By				brandingLogo		= By.id("t-sembly-logo");
	// ------ Login section ------
	private By				loginHereButton		= By.cssSelector("a[ng-click='showLogInFormDialog()']");
	private By				loginEmailField		= By.id("email");
	private By				loginPasswordField	= By.id("password");
	private By				loginButton			= By.cssSelector("button[ng-click='loginForm.$valid && login()']");
	// ------ Signup section ------
	private By				teacherButton		= By.xpath("//*[@id='signUpSection']/div/div[2]/div[2]/div/div/div[1]/button[1]");
	private By				studentButton		= By.xpath("//*[@id='signUpSection']/div/div[2]/div[2]/div/div/div[1]/button[2]");
	private By				parentButton		= By.cssSelector("//*[@id='signUpSection']/div/div[2]/div[2]/div/div/div[1]/button[3]");
	private By				firstName			= By.id("firstName");
	private By				lastName			= By.id("lastName");
	private By				signupEmail			= By.id("signupEmail");
	private By				signupPassword		= By.id("signupPassword");
	private By				confirmPassword		= By.id("confirmPassword");
	private By				inputStudentCode	= By.id("studentCode");
	private By				signupButton		= By.cssSelector("button[ng-click='signupForm.$valid && signup()']");
	private By				iosAppButton		= By.className("apple-icon");
	private By				androidAppButton	= By.className("android-icon");

	/* --- Element locators --- END */

	// Constructor
	public HomePage(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	public String getPageTitle() {
		String title = wd.getTitle();
		return title;
	}

	public void verifyHomePageIsOpened() {
		w.until(ExpectedConditions.elementToBeClickable(brandingLogo));
	}

	/* Workflows */

	public void signup() {
		String firstname = "Sergei";
		String lastname = "Sc";
		Random r = new Random();
		int myNum = r.nextInt(1000);

		verifyHomePageIsOpened();

		clickUserRole("teacher");

		inputFirstName(firstname);
		Assert.assertTrue(verifyFirstName(firstname));

		inputLastName(lastname);
		Assert.assertTrue(verifyLastName(lastname));

		inputSignupEmail("SergeiSc" + myNum + "@automationtest.com");
		Assert.assertTrue("Email field verification failed", verifySignupEmail("SergeiSc" + myNum
				+ "@automationtest.com"));

		inputSignupPassword("ireland");
		Assert.assertTrue(verifySignupPassword("ireland"));

		inputPasswordConfirmation("ireland");
		Assert.assertTrue(verifyPasswordConfirmation("ireland"));

		dash = clickOnSignup();
		Assert.assertTrue("Dashboard verification failed", dash.verifyDashboardIsOpened());

		System.out.println("Signup as teacher PASS");
		System.out.println("---\nSergeiSc" + myNum + "@automationtest.com \nireland \n---");
	}

	public void login() {
		String username = "u2@t.com";
		String password = "ireland";
		// Verify that home page is opened
		verifyHomePageIsOpened();
		// Login with given credentials
		clickLoginHereButton();
		inputLoginUsername(username);
		Assert.assertTrue("Login Username Text is not correct", verifyLoginUsernameText(username));
		inputLoginPassword(password);
		Assert.assertTrue("Login Password Text is not correct", verifyLoginPasswordText(password));
		BaseDashboard dash = clickOnLogin();
		// Verify that login was successful and dashboard is opened
		Assert.assertTrue("Dashboard verification failed", dash.verifyDashboardIsOpened());
		System.out.println("Login PASS");
	}

	public void checkMobileAppStores() {
		clickIosButton();
		verifyIosPage();
		System.out.println("iOS store page PASS");
		clickAndroidButton();
		verifyAndroidPage();
		System.out.println("Android store page PASS");
	}

	/* Signup Methods */

	public void clickUserRole(String r) {
		switch (r.toLowerCase()) {
			case "teacher":
				wd.findElement(teacherButton).click();
				break;
			case "student":
				wd.findElement(studentButton).click();
				break;
			case "parent":
				wd.findElement(parentButton).click();
				break;
			default:
				Assert.fail("ERROR: User role is incorrect. Should be teacher, student or parent.");
				break;
		}
	}

	public void inputFirstName(String f) {
		WebElement e = wd.findElement(firstName);
		e.sendKeys(f);
	}

	public boolean verifyFirstName(String f) {
		WebElement e = wd.findElement(firstName);
		return e.getAttribute("value").contentEquals(f);
	}

	public void inputLastName(String l) {
		WebElement e = wd.findElement(lastName);
		e.sendKeys(l);
	}

	public boolean verifyLastName(String l) {
		WebElement e = wd.findElement(lastName);
		return e.getAttribute("value").contentEquals(l);
	}

	public void inputStudentCode(String c) {
		WebElement e = wd.findElement(inputStudentCode);
		e.sendKeys(c);
	}

	public boolean verifyStudentCode(String c) {
		WebElement e = wd.findElement(inputStudentCode);
		return e.getAttribute("value").contentEquals(c);
	}

	public void inputSignupEmail(String email) {
		WebElement e = wd.findElement(signupEmail);
		e.sendKeys(email);
	}

	public boolean verifySignupEmail(String email) {
		WebElement e = wd.findElement(signupEmail);
		return e.getAttribute("value").contentEquals(email);
	}

	public void inputSignupPassword(String pass) {
		WebElement e = wd.findElement(signupPassword);
		e.sendKeys(pass);
	}

	public boolean verifySignupPassword(String pass) {
		WebElement e = wd.findElement(signupPassword);
		return e.getAttribute("value").contentEquals(pass);
	}

	public void inputPasswordConfirmation(String pass) {
		WebElement e = wd.findElement(confirmPassword);
		e.sendKeys(pass);
	}

	public boolean verifyPasswordConfirmation(String pass) {
		WebElement e = wd.findElement(confirmPassword);
		return e.getAttribute("value").contentEquals(pass);
	}

	// Click on Signup button
	public BaseDashboard clickOnSignup() {
		WebElement e = wd.findElement(signupButton);
		e.click();
		return new BaseDashboard(wd, w);
	}

	/* Login Methods */

	public void clickLoginHereButton() {
		w.until(ExpectedConditions.elementToBeClickable(loginHereButton));

		WebElement e = wd.findElement(loginHereButton);
		if (e.isDisplayed()) {
			e.click();
		}
		else {
			Assert.fail("clickLoginHereButton is not displayed");
		}
	}

	// Login main method
	public BaseDashboard login(String username, String password) throws InterruptedException {
		clickLoginHereButton();
		inputLoginUsername(username);
		inputLoginPassword(password);
		return clickOnLogin();
	}

	// Enter name in login username field
	public void inputLoginUsername(String userName) {
		WebElement emailTxtBox = wd.findElement(loginEmailField);
		emailTxtBox.sendKeys(userName);
	}

	public boolean verifyLoginUsernameText(String t) {
		WebElement emailTxtBox = wd.findElement(loginEmailField);
		return emailTxtBox.getAttribute("value").contentEquals(t);
	}

	// Enter password in login section
	public void inputLoginPassword(String password) {
		WebElement passwordTxtBox = wd.findElement(loginPasswordField);
		passwordTxtBox.sendKeys(password);
	}

	public boolean verifyLoginPasswordText(String t) {
		WebElement passwordTxtBox = wd.findElement(loginPasswordField);
		return passwordTxtBox.getAttribute("value").contentEquals(t);
	}

	// Click on login button
	public BaseDashboard clickOnLogin() {
		WebElement signInBtn = wd.findElement(loginButton);
		signInBtn.click();
		return new BaseDashboard(wd, w);
	}

	/* Mobile app stores */
	// iOS
	public void clickIosButton() {
		wd.findElement(iosAppButton).click();
	}

	public void verifyIosPage() {
		switchToNewWindow();
		Boolean iosPageTitle = wd.getTitle().contentEquals("Sembly on the App Store");
		Assert.assertTrue("iOS app store page is not verified", iosPageTitle);
		closeAppStoreWindow();
	}

	// Android
	public void clickAndroidButton() {
		wd.findElement(androidAppButton).click();
	}

	public void verifyAndroidPage() {
		switchToNewWindow();
		Boolean androidPageTitle = wd.getTitle().contentEquals("Sembly – Android Apps on Google Play");
		Assert.assertTrue("Android app store page is not verified", androidPageTitle);
		closeAppStoreWindow();
	}

	// This method switches to the new window that just opened after
	//	clicking on app store button
	public void switchToNewWindow() {
		browserWindows = new ArrayList<String>(wd.getWindowHandles());
		wd.switchTo().window(browserWindows.get(1));
	}

	public void closeAppStoreWindow() {
		wd.close();
		browserWindows = new ArrayList<String>(wd.getWindowHandles());
		wd.switchTo().window(browserWindows.get(0));
	}

}
