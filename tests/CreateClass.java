package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class CreateClass extends TestSetup {

	@Test
	public void canCreateClass() {
		Pages.topNavigation().addNewClass();
	}
	
}
