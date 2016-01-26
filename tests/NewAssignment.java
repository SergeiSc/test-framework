package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class NewAssignment extends TestSetup {

	@Test
	public void canCreateClass() {
		Pages.topNavigation().addNewClass();
	}

	@Test(dependsOnMethods = { "canCreateClass" })
	public void canCreateAssignment() {
		Pages.topNavigation().createNewAssignment();
	}

}
