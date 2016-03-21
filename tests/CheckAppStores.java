package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class CheckAppStores extends TestSetup {

	@Test
	public void canGotoAppStores() {
		Pages.home().verifyHomePageIsOpened();
		Pages.home().checkMobileAppStores();
	}

}
