package tests;

import org.testng.annotations.Test;

import pages.Pages;
import testSetup.TestSetup;

public class Logout extends TestSetup {

	@Test
	public void canLogout(){
		Pages.topNavigation().logout();
	}
	
}
