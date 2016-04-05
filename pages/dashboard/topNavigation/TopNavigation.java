package pages.dashboard.topNavigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.HomePage;

public class TopNavigation {
	protected WebDriver		wd;
	protected WebDriverWait	w;

	/* --- Element locators --- START */
	// Plus menu
	private By				plusMenu					= By.cssSelector("span[class='lnr icon-plus-circle dropdown-toggle']");
	private By				plusMenu_AddNewClass		= By.xpath("//*[@id='first-view-container']/div[5]/ng-include/div/div[2]/ul/li[3]/ul/li[1]/a");/*By.cssSelector("a[ng-click='showClassFormDialog()']");*/
	private By				plusMenu_JoinClass			= By.xpath("//*[@id='first-view-container']/div[5]/ng-include/div/div[2]/ul/li[3]/ul/li[2]/a");/*By.cssSelector("a[ng-click='showJoinClassDialog()']");*/
	private By				plusMenu_NewAssignment		= By.cssSelector("a[ng-click='toggleAssignmentCreation()']");
	private By				plusMenu_NewAnnouncement	= By.cssSelector("a[ng-click='showAnnouncementDialog()']");
	private By				plusMenu_ScheduleClass		= By.cssSelector("a[ng-click='openScheduler()']");
	private By				plusMenu_AddSchool			= By.cssSelector("a[ng-click='showSchoolModal()']");
	// User menu (top right corner)
	private By				userMenu					= By.xpath("//*[@id='first-view-container']/div[5]/ng-include/div/div[2]/ul/li[4]/span");
	private By				userMenu_Profile			= By.xpath("//*[@id='first-view-container']/div[5]/ng-include/div/div[2]/ul/li[4]/ul/li[2]");
	private By				userMenu_Logout				= By.xpath("//*[@id='first-view-container']/div[5]/ng-include/div/div[2]/ul/li[4]/ul/li[3]");

	/* --- Element locators --- END */

	// Constructor
	public TopNavigation(WebDriver wd, WebDriverWait wait) {
		this.wd = wd;
		this.w = wait;
	}

	/* Plus menu */

	// Click the plus button and menu will appear
	public void clickPlusMenu() {
		w.until(ExpectedConditions.elementToBeClickable(plusMenu)).click();
	}

	// Click on Plus menu item
	public void plusMenu(String m) {
		clickPlusMenu();
		w.until(ExpectedConditions.elementToBeClickable(plusMenu_JoinClass));
		switch (m.toLowerCase()) {
			case "add new class":
				wd.findElement(plusMenu_AddNewClass).click();
				break;
			case "join class":
				wd.findElement(plusMenu_JoinClass).click();
				break;
			case "new assignment":
				wd.findElement(plusMenu_NewAssignment).click();
				break;
			case "new announcement":
				wd.findElement(plusMenu_NewAnnouncement).click();
				break;
			case "schedule class":
				wd.findElement(plusMenu_ScheduleClass).click();
				break;
			case "add school":
				wd.findElement(plusMenu_AddSchool).click();
				break;
			default:
				System.out.println("ERROR: Incorrect menu item");
				Assert.assertTrue(false);
				break;
		}

	}

	public void addNewClass() {
		AddNewClass a = new AddNewClass(wd, w);
		a.addNewClass();
	}

	public void createNewAssignment() {
		CreateAssignment steps = new CreateAssignment(wd, w);
		steps.createAssignment();
	}

	/* User menu */

	public void clickUserMenu() {
		w.until(ExpectedConditions.elementToBeClickable(userMenu)).click();
	}

	public void userMenu(String m) {
		clickUserMenu();
		w.until(ExpectedConditions.elementToBeClickable(userMenu_Profile));
		switch (m.toLowerCase()) {
			case "profile":
				wd.findElement(userMenu_Profile).click();
				break;
			case "logout":
				wd.findElement(userMenu_Logout).click();
				break;
			default:
				System.out.println("ERROR: Incorrect menu item");
				Assert.assertTrue(false);
				break;
		}
	}
	// Logout
	public void logout(){
		userMenu("logout");
		HomePage h = new HomePage(wd, w);
		h.verifyHomePageIsOpened();
		System.out.println("Logout PASS");
	}


}
