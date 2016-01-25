package pages.dashboard.topNavigation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.AbstractComponent;

public class AssignmentSteps extends AbstractComponent {
	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* START --- Element locators --- START */

	// ------ Step 1 ------
	private By				verify_step1					= By.xpath("//*[@id='create-assignment-container']/div/div/h2");
	private By				step1_titleField				= By.name("title");
	private By				step1_classGroupSelect			= By.name("classGroup");
	private By				step1_onlineSubmissionRadio		= By.cssSelector("input[value='online']");
	@SuppressWarnings("unused")
	private By				step1_handinRadio				= By.cssSelector("input[value='hand_in']");
	private By				step1_nextButton				= By.cssSelector("button[ng-click='saveAssignment()']");
	// ------ Datepicker ------
	private By				step1_datePickerIcon			= By.cssSelector("span[ng-click='startDateOpened=true']");
	private By				datepickerTable					= By.xpath("/html/body/div[8]/div/div/div/div[2]/div/table");
	private By				verify_datePickerModal			= By.xpath("/html/body/div[8]/div/div/div/div[1]/div[1]/h2");
	private By				nextMonthButton					= By.cssSelector("button[ng-click='move(1)']");
	private By				saveButton						= By.cssSelector("button[ng-click='$close(dueDate)']");
	// ------ Step 2 ------
	private By				verify_editorFirstButton		= By.cssSelector("a[rel='formatting']");
	private By				step2_editor					= By.cssSelector(".redactor-editor.redactor-placeholder");
	private By				step2_nextButton				= By.cssSelector("button[ng-click='nextStep()']");
	// ------ Step 3 ------
	private By				step3_addResourcesButton		= By.cssSelector("button[model='newAssignmentResource']");
	private By				step3_addSolutionsButton		= By.cssSelector("button[ng-click='addSolutionDialog()']");
	private By				step3_nextButton				= By.xpath("//*[@id='create-assignment-container']/div[2]/div[2]/div/div[1]/div[2]/button");
	// ------ Step 4 ------
	private By				verify_step4Message				= By.xpath("//*[@id='content-container']/div/div[3]/div/div/div[4]/ng-include/div/div[1]/div[1]/div");
	private By				step4_nextButton				= By.xpath("//*[@id='content-container']/div/div[3]/div/div/div[4]/ng-include/div/div[3]/div[2]/button");
	// ------ Step 5 ------
	private By				verify_step5Message				= By.xpath("//*[@id='content-container']/div/div[3]/div/div/div[5]/ng-include/div/div[2]/div/div[1]/span/strong");
	private By				step5_assignToAllToggle			= By.cssSelector("input[ng-click='assignToAll()']");
	private By				step5_saveButton				= By.cssSelector("button[ng-click='assign()']");
	// ------ Assignment details page ------
	private By				verfiry_assignmentDetailsPage	= By.cssSelector("button[ng-click='startEditing();']");

	/* END --- Element locators --- END */
	// Constructor
	public AssignmentSteps(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	/* Workflows */

	public void createAssignment() {
		TopNavigation top = new TopNavigation(wd, w);
		String assignmentName = "Anatomy Test";
		
		top.plusMenu("New Assignment");
		
		Assert.assertTrue(verifyStep1(), "This is not the 1st step in assignment creation form");
		fillStep1(assignmentName, 1);

		Assert.assertTrue(verifyStep2(), "This is not the 2st step in assignment creation form");
		fillStep2("Assignment content");

		Assert.assertTrue(verifyStep3(), "Step 3 is not open or buttons not displaying");
		fillStep3();

		Assert.assertTrue(verifyStep4(), "Step 4 failed verification");
		fillStep4();

		Assert.assertTrue(verifyStep5(), "Step 5 failed verification");
		fillStep5();

		Assert.assertTrue(verifyAssignmentPage(),
				"Post assignment creation page is not verified. Marks as complete is not displayed.");
		System.out.println("Create Assignment PASS");
	}

	/* --- Verify - Methods --- */

	// Verify that assignment first step is opened
	public boolean verifyStep1() {
		return verifyPageText(verify_step1, "Assignment details");
	}

	// Verify that due date modal is opened
	public boolean verifyDueDateWindow() {
		return verifyPageText(verify_datePickerModal, "When is this assignment due?");
	}

	// Verify that assignment 2nd step is opened
	public boolean verifyStep2() {
		return verifyButton(verify_editorFirstButton);
	}

	// Verify that 3rd step is open and displaying 2 buttons
	public boolean verifyStep3() {
		if (verifyButton(step3_addResourcesButton) && verifyButton(step3_addSolutionsButton)) {
			return true;
		}
		else return false;
	}

	// Verify step 4 message
	public boolean verifyStep4() {
		return verifyPageText(verify_step4Message, "This is how the assignment will look to your students.");
	}

	// Verify step 5 message
	public boolean verifyStep5() {
		return verifyPageText(verify_step5Message, "Assign to all students");
	}

	// Verify assignment details page
	public boolean verifyAssignmentPage() {
		return verifyButton(verfiry_assignmentDetailsPage);
	}

	/* --- Methods to fill out assignment steps --- */

	// Fill out 1st step
	public void fillStep1(String assignmentName, int classGroup) {
		// ------ Enter title ------
		WebElement title = wd.findElement(step1_titleField);
		if (title.isDisplayed())
			title.sendKeys(assignmentName);
		// ------ Select class group ------
		select(step1_classGroupSelect, classGroup);
		// ------ Pick a due date ------
		pickDueDate();
		// ------ Select submission ------
		wd.findElement(step1_onlineSubmissionRadio).click();
		//wd.findElement(step1_handinRadio).click();
		// ------ Click Next ------
		wd.findElement(step1_nextButton).click();
	}

	// Pick a date in the due date modal
	public void pickDueDate() {
		// ------ Click datepicker icon ------ 
		wd.findElement(step1_datePickerIcon).click();
		// ------ Click next month arrow button ------ 
		wd.findElement(nextMonthButton).click();
		// ------ Click day of the month ------ 
		WebElement dateWidget = wd.findElement(datepickerTable);
		List<WebElement> columns = dateWidget.findElements(By.tagName("td"));
		for (WebElement cell : columns) {
			if (cell.getText().contains("22")) {
				cell.click();
				break;
			}
		}
		// ------ Click Ok button to save due date and close modal ------ 
		wd.findElement(saveButton).click();
	}

	// Fill out 2nd step
	public void fillStep2(String editorText) {
		// ------ Send text to editor ------ 
		WebElement w = wd.findElement(step2_editor);
		w.sendKeys(editorText);
		// ------ Click Next ------
		wd.findElement(step2_nextButton).click();
	}

	// 3rd step - Resources
	public void fillStep3() {
		wd.findElement(step3_nextButton).click();
	}

	// 4th step - Preview
	public void fillStep4() {
		wd.findElement(step4_nextButton).click();
	}

	// 5th step - Assign to students
	public void fillStep5() {
		wd.findElement(step5_assignToAllToggle).click();
		wd.findElement(step5_saveButton).click();
	}

}
