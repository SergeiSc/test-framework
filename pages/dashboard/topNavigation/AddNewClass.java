package pages.dashboard.topNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;

public class AddNewClass extends AbstractComponent {
	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* --- Element locators --- START */

	private By				classNameField			= By.name("name");
	private By				selectSchoolDropDown	= By.name("school");
	private By				selectSubject			= By.name("subject");
	//private By addSchoolButton = By.xpath("//button[contains(.,'Add School')]");
	private By				createClassButton		= By.xpath("//button[contains(.,'Create Class Group')]");

	private By				verify_classIsCreated	= By.xpath("//h2[contains(.,'Congratulations!')]");
	private By				verify_addNewClassModal	= By.xpath("//h2[contains(.,'Create Class Group')]");

	/* --- Element locators --- END */
	
	/* Workflows */
	
	public void addNewClass(){
		// Click on Add New Class in top menu
		TopNavigation top = new TopNavigation(wd, w);
		top.plusMenu("Add New Class");
		// Verify that Add New Class modal is opened
		Assert.assertTrue(verifyAddNewClassModalIsOpened(), "Add new class modal is not displayed");
		// Create new class
		String className = "Biology 5th";
		inputClassName(className);
		verifyClassPageIsOpened(className);
		selectSchool(1);
		selectSubject(1);
		clickOnCreateClass();
		// Verify that class is created
		Assert.assertTrue(verifyClassIsCreated(), "Post class creation page is not verified");
		System.out.println("Add New Class PASS");
	}
	
	/* Methods */

	public AddNewClass(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	// Verify that add class modal is opened
	public boolean verifyAddNewClassModalIsOpened() {
		return verifyPageText(verify_addNewClassModal, "Create Class Group");
	}

	// Verification after class was created
	public boolean verifyClassIsCreated() {
		return verifyPageText(verify_classIsCreated, "Congratulations!");
	}

	// Main create class method
	public void createClass(String className) {
		w.until(ExpectedConditions.elementToBeClickable(classNameField));
		inputClassName(className);
		selectSchool(1);
		selectSubject(1);
		clickOnCreateClass();
	}

	// Enter name in Class Name field
	public void inputClassName(String className) {
		WebElement nameField = wd.findElement(classNameField);
		nameField.sendKeys(className);
	}
	
	public boolean verifyClassPageIsOpened(String className){
		WebElement e = wd.findElement(classNameField);
		return e.getAttribute("value").contentEquals(className);
	}

	// Select school
	public void selectSchool(int i) {
		select(selectSchoolDropDown, i);
	}

	// Select subject
	public void selectSubject(int i) {
		select(selectSubject, i);
	}

	// Click on create class button
	public void clickOnCreateClass() {
		WebElement btn = wd.findElement(createClassButton);
		btn.click();
	}

}
